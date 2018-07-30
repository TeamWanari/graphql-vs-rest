package com.wanari.graphql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanari.graphql.domain.*;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.graphql.repository.PrivilegeRepository;
import com.wanari.graphql.repository.RoleRepository;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
public class DataGenerationController {

    private final UserRepository userRepository;
    private final PrinterRepository printerRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;

    public DataGenerationController(UserRepository userRepository, PrinterRepository printerRepository, PrivilegeRepository privilegeRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.printerRepository = printerRepository;
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/data")
    @ResponseBody
    public void generateData(@RequestBody String jsonData) throws IOException {
        DbGenerationData data = new ObjectMapper().readValue(jsonData, DbGenerationData.class);
        generateData(data);
    }

    @Transactional
    public void generateData(DbGenerationData data) {
        deleteAllRepo();
        initTables(data);
        joinUsersAndRoles(data);
        joinRolesAndPrivileges(data);
    }

    private void deleteAllRepo() {
        printerRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        privilegeRepository.deleteAll();
    }

    private void joinRolesAndPrivileges(DbGenerationData data) {
        List<Privilege> privileges = privilegeRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for(Role role : roles) {
            int numberOfPrivileges = generateRand(data.minPrivilegeByRole, data.maxPrivilegeByRole);
            for(int i = 0; i < numberOfPrivileges; i++) {
                int generated = generateRand(0, privileges.size() - 1);
                role.privileges.add(privileges.get(generated));
            }
            roleRepository.save(role);
        }
    }

    private void joinUsersAndRoles(DbGenerationData data) {
        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        for(User user : users) {
            int numberOfRoles = generateRand(data.minRolesByUser, data.maxRolesByUser);
            for(int i = 0; i < numberOfRoles; i++) {
                int generated = generateRand(0, roles.size() - 1);
                user.roles.add(roles.get(generated));
            }
            userRepository.save(user);
        }
    }

    public void initTables(DbGenerationData data) {

        for(int i = 0; i < data.userNumber; i++) {
            User user = new User();
            user.login = "login" + i;
            user.password = "super_secret_password" + i;
            userRepository.save(user);

            int numberOPrinters = generateRand(data.minPrintersByUser, data.maxPrintersByUser);
            for(int j = 0; j < numberOPrinters; j++) {
                Printer printer = new Printer();
                printer.name = "printer_name" + i + "_" + j;
                printer.serialNumber = UUID.randomUUID().toString();
                printer.owner = user;
                printerRepository.save(printer);
            }
        }

        for(int i = 0; i < data.privilegeNumber; i++) {
            Privilege privilege = new Privilege();
            if(i == 0) {
                privilege.keyName = "VIEW_PRINTER_PRIVILEGE";
            } else {
                privilege.keyName = "DETONATE_PRINTER_PRIVILEGE_" + i;
            }
            privilegeRepository.save(privilege);
        }

        for(int i = 0; i < data.roleNumber; i++) {
            Role role = new Role();
            if(i == 0) {
                role.keyName = "ADMIN_ROLE";
            } else {
                role.keyName = "COMMON_ROLE_" + i;
            }
            roleRepository.save(role);
        }
    }

    private int generateRand(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max - min) + min;
        return result;
    }
}
