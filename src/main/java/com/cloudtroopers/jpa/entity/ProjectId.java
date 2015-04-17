package com.cloudtroopers.jpa.entity;

import java.io.Serializable;

/**
 * 
 * @author Radu Cosma <radu.cosma@cloudtroopers.com>
 *
 */
public class ProjectId implements Serializable {

    private static final long serialVersionUID = 1975231973623856509L;
    
    private String name;
    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProjectId [name=").append(name).append(", department=").append(department).append("]");
        return builder.toString();
    }

}
