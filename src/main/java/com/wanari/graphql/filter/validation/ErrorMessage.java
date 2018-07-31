package com.wanari.graphql.filter.validation;

import io.vavr.Function2;

@FunctionalInterface
public interface ErrorMessage extends Function2<String, Object, String> {
}