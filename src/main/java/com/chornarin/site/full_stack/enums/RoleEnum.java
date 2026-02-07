package com.chornarin.site.full_stack.enums;

import java.util.Set;


public enum RoleEnum {
    USER(Set.of(PermissionEnum.READ)), 
    ADMIN(Set.of(
        PermissionEnum.READ, 
        PermissionEnum.WRITE,
        PermissionEnum.UPDATE,
        PermissionEnum.DELETE
    ));

    private final Set<PermissionEnum> permissions;

    RoleEnum(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public String getAuthority(){
        return "ROLE_" +  this.name();
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }
}
