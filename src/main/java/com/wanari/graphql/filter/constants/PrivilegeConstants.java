package com.wanari.graphql.filter.constants;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeConstants {
    public static final String ENTITY_NAME = "privilege";

    public class FieldName {
        public static final String KEY = "key";
    }

    public class Wrapper {
        public static final String DETAILS = "details";
    }

    public static final List<String> details = initializeDetailsFieldList();

    private static List<String> initializeDetailsFieldList() {
        List<String> detailFields = new ArrayList<>();
        return detailFields;
    }
}
