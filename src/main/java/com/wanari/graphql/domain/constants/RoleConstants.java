package com.wanari.graphql.domain.constants;

import com.wanari.graphql.domain.Privilege;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.Role_;
import com.wanari.utils.spring.genericfilter.GenericFilterUtil;
import com.wanari.utils.spring.genericfilter.JoinTableInfo;

import java.util.ArrayList;
import java.util.List;

public class RoleConstants {
    public static final String ENTITY_NAME = "role";

    public class FieldName {
        public static final String KEY = "key";
        public static final String USERS = "users";
        public static final String PRIVILEGES = "privileges";
    }

    public static class JoinInfo {
        public static final JoinTableInfo<Role, Privilege> PRIVILEGES = JoinTableInfo.of(Role_.privileges, ENTITY_NAME, FieldName.PRIVILEGES);
    }

    public class Wrapper {
        public static final String DETAILS = "details";
    }

    public static final List<String> details = initializeDetailsFieldList();

    private static List<String> initializeDetailsFieldList() {
        List<String> detailFields = new ArrayList<>();
        detailFields.add(FieldName.PRIVILEGES);
        detailFields.addAll(GenericFilterUtil.combine(PrivilegeConstants.ENTITY_NAME, PrivilegeConstants.details));
        return detailFields;
    }
}
