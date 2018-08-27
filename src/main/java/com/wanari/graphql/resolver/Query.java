package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wanari.generic_filter.GenericFilter;
import com.wanari.generic_filter.GenericParameterBuilder;
import com.wanari.generic_filter.GenericParameters;
import com.wanari.generic_filter.constants.GeneralFilterConstants;
import com.wanari.graphql.domain.constants.PrinterConstants;
import com.wanari.graphql.domain.constants.UserConstants;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.graphql.repository.UserRepository;
import com.wanari.graphql.service.GraphqlPrinterService;
import com.wanari.graphql.service.GraphqlUserService;
import com.wanari.common.error.ErrorDescriptionDto;
import com.wanari.common.error.ErrorDescriptionParamDto;
import com.wanari.common.error.ErrorDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final PrinterRepository printerRepository;
    private final GraphqlUserService userService;
    private final GraphqlPrinterService printerService;

    public Query(UserRepository userRepository, PrinterRepository printerRepository, GraphqlUserService userService, GraphqlPrinterService printerService) {
        this.userRepository = userRepository;
        this.printerRepository = printerRepository;
        this.userService = userService;
        this.printerService = printerService;
    }

    public List<GraphqlUser> getUsers(Long id) {
        if(id == null) {
            return GraphqlUser.from(userRepository.findAll());
        } else {
            return Collections.singletonList(
                GraphqlUser.from(userRepository.findOne(id))
            );
        }
    }

    public List<GraphqlPrinter> getPrinters(Long id, Long ownerId) {
        if(id != null && ownerId != null) {
            return GraphqlPrinter.from(printerRepository.findByIdAndOwnerId(id, ownerId));
        } else if(id != null) {
            return Collections.singletonList(GraphqlPrinter.from(printerRepository.findOne(id)));
        } else if(ownerId != null) {
            return GraphqlPrinter.from(printerRepository.findByOwnerId(ownerId));
        } else {
            return GraphqlPrinter.from(printerRepository.findAll());
        }
    }

    public List<GraphqlUser> getUsersFiltered(Long id, String sortBy, String sortOrder, Integer page, Integer pageSize) {
        GenericFilter filter = new GenericFilter();

        filter.on(UserConstants.FieldName.ID)
            .withValue(id)
            .onlyIfNotNull()
            .addFilter();

        GenericParameters parameter = GenericParameterBuilder
            .filterWith(filter)
            .sortBy(sortBy != null ? sortBy : UserConstants.FieldName.ID)
            .sortOrder(sortOrder != null ? sortOrder : GeneralFilterConstants.ASC)
            .page(page != null ? page : GeneralFilterConstants.DEFAULT_PAGE)
            .pageSize(pageSize != null ? pageSize : GeneralFilterConstants.DEFAULT_PAGE_SIZE)
            .joinTables(false)
            .build();

        return parameter.validate(UserConstants.ENTITY_NAME).map(userService::find).fold(
            errorDto -> {
                throw new RuntimeException("Something went wrong. Error: " + errorToString(errorDto));
            },
            Function.identity()
        );
    }

    public List<GraphqlPrinter> getPrintersFiltered(Long id, Long ownerId, String sortBy, String sortOrder, Integer page, Integer pageSize) {
        GenericFilter filter = new GenericFilter();

        filter.on(PrinterConstants.FieldName.ID)
            .withValue(id)
            .onlyIfNotNull()
            .addFilter();

        filter.on(PrinterConstants.FieldName.OWNER_ID)
            .withValue(ownerId)
            .onlyIfNotNull()
            .addFilter();

        GenericParameters parameter = GenericParameterBuilder
            .filterWith(filter)
            .sortBy(sortBy != null ? sortBy : PrinterConstants.FieldName.ID)
            .sortOrder(sortOrder != null ? sortOrder : GeneralFilterConstants.ASC)
            .page(page != null ? page : GeneralFilterConstants.DEFAULT_PAGE)
            .pageSize(pageSize != null ? pageSize : GeneralFilterConstants.DEFAULT_PAGE_SIZE)
            .joinTables(false)
            .build();

        return parameter.validate(PrinterConstants.ENTITY_NAME).map(printerService::find).fold(
            errorDto -> {
                throw new RuntimeException("Something went wrong. Error: " + errorToString(errorDto));
            },
            Function.identity()
        );
    }

    private String errorToString(ErrorDto error) {
        return "ErrorDto{" +
            "status=" + error.status +
            ", entityName='" + error.entityName + '\'' +
            ", errorDescriptions=" + descriptionToString(error.errorDescriptions) +
            '}';
    }

    private String descriptionToString(List<ErrorDescriptionDto> descriptions) {
        return descriptions.stream().map(this::descriptionToString).collect(Collectors.joining(", "));
    }

    private String descriptionToString(ErrorDescriptionDto description) {
        return "ErrorDescriptionDto{" +
            "errorKey='" + description.errorKey + '\'' +
            ", params=" + paramToString(description.params) +
            '}';
    }

    private String paramToString(List<ErrorDescriptionParamDto> params) {
        return params.stream().map(this::paramToString).collect(Collectors.joining(", "));
    }

    private String paramToString(ErrorDescriptionParamDto param) {
        return "ErrorDescriptionParamDto{" +
            "paramKey='" + param.paramKey + '\'' +
            ", paramValue='" + param.paramValue + '\'' +
            '}';
    }
}
