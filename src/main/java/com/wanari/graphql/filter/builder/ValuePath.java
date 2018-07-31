package com.wanari.graphql.filter.builder;


import com.wanari.graphql.filter.validation.Validation;

import java.util.Objects;
import java.util.function.Supplier;

public class ValuePath<T> extends BasePath<T> {

    ValuePath(BasePath<T> other) {
        super(other);
    }

    public ParsePath<T> validateWith(Supplier<Validation> validatorSupplier) {
        this.validation = validatorSupplier.get();
        return new ParsePath<>(this);
    }

    public EndPath<T> onlyIfNotNull() {
        this.rule = Objects::nonNull;
        return new EndPath<>(this);
    }

    public ValuePath<T> onlyIfNotNullAnd() {
        this.rule = Objects::nonNull;
        return new ValuePath<>(this);
    }
}
