package com.wanari.graphql.filter.validation;

import java.util.function.Function;

@FunctionalInterface
public interface Parser<T> extends Function<Object, T> {
}