package com.wanari.graphql.filter.validation;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validator extends Predicate<Object> {
}