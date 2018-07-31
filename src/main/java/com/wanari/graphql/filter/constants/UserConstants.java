package com.wanari.graphql.filter.constants;

import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.User;
import com.wanari.graphql.domain.User_;
import com.wanari.graphql.filter.GenericFilterUtil;
import com.wanari.graphql.filter.JoinTableInfo;

import java.util.ArrayList;
import java.util.List;

public class UserConstants {
    public static final String ENTITY_NAME = "user";

    public class FieldName {
        public static final String ID = "id";
        public static final String LOGIN = "login";
        public static final String ROLES = "roles";
        public static final String PRINTERS = "printers";
    }

    public static class JoinInfo {
        public static final JoinTableInfo<User, Role> ROLES = JoinTableInfo.of(User_.roles, ENTITY_NAME, FieldName.ROLES);
        public static final JoinTableInfo<User, Printer> PRINTERS = JoinTableInfo.of(User_.printers, ENTITY_NAME, FieldName.PRINTERS);
    }

    public class Wrapper {
        public static final String DETAILS = "details";
    }

    public static final List<String> details = initializeDetailsFieldList();

    private static List<String> initializeDetailsFieldList() {
        List<String> detailFields = new ArrayList<>();
        detailFields.add(FieldName.ID);
        detailFields.add(FieldName.LOGIN);
        detailFields.add(FieldName.ROLES);
        detailFields.addAll(GenericFilterUtil.combine(RoleConstants.ENTITY_NAME, RoleConstants.details));
        detailFields.add(FieldName.PRINTERS);
        detailFields.addAll(GenericFilterUtil.combine(PrinterConstants.ENTITY_NAME, PrinterConstants.details));
        return detailFields;
    }
}
