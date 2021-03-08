package com.koko.enums;

/**
 * @author 13629
 * @create 2021/2/20 14:41
 */
public enum RoleID {
    ADAMINISTRATOR("root", 1),
    MEMBER_USER("cli", 2),
    NEGOZIANTE("neg", 3),
    EMPLOYEE("emp", 4)
    ;
    private String role;
    private Integer id;

    RoleID(String role, Integer id){
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
