package com.wanari.graphql.filter;

import com.wanari.graphql.service.error.ErrorDescriptionParamDto;
import com.wanari.graphql.service.error.ErrorDto;
import io.vavr.control.Either;

import java.util.List;

public class GenericParameters {
    List<String> fields;
    String sortBy;
    String sortOrder;
    GenericFilter filter;
    Integer page;
    Integer pageSize;
    Boolean shouldJoinTables;

    public Either<ErrorDto, ValidGenericParameters> validate(String entity) {
        List<String> errors = filter.getErrors();
        if(!errors.isEmpty()) {
            return ErrorDto.badRequestEither(entity, "error.parameter.validation", new ErrorDescriptionParamDto("errors", String.join(", ", errors)));
        } else {
            return Either.right(toValidParameters());
        }
    }

    private ValidGenericParameters toValidParameters() {
        return new ValidGenericParameters(this);
    }

    public List<String> getFields() {
        return fields;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public GenericFilter getFilter() {
        return filter;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
