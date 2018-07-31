package com.wanari.graphql.filter.constants;

import java.util.ArrayList;
import java.util.List;

public class PrinterConstants {
    public static final String ENTITY_NAME = "printer";

    public class FieldName {
        public static final String ID = "id";
        public static final String OWNER = "owner";
        public static final String OWNER_ID = "ownerId";
        public static final String NAME = "name";
        public static final String SERIAL_NUMBER = "serialNumber";
    }

    public class Wrapper {
        public static final String DETAILS = "details";
    }

    public static final List<String> details = initializeDetailsFieldList();

    private static List<String> initializeDetailsFieldList() {
        List<String> detailFields = new ArrayList<>();
        detailFields.add(FieldName.ID);
        detailFields.add(FieldName.OWNER);
        detailFields.add(FieldName.NAME);
        detailFields.add(FieldName.SERIAL_NUMBER);
        return detailFields;
    }
}
