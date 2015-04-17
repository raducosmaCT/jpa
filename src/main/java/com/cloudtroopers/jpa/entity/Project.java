package com.cloudtroopers.jpa.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Radu Cosma <radu.cosma@cloudtroopers.com>
 *
 */
@Entity
@Table(name = "PROJECT")
@IdClass(ProjectId.class)
@NamedQueries(value = { @NamedQuery(name = Project.FIND_ALL, query = "SELECT p FROM Project p") })
public class Project {

    public static final String FIND_ALL = "Project.findAll";

    @Id
    private String name;

    @Id
    @ManyToOne
    @MapsId("departmentId")
    private Department department;

    @ManyToMany
    @JoinTable(name = "PROJECT_EMPLOYEE", joinColumns = { @JoinColumn(name = "project_department_id"),
            @JoinColumn(name = "project_name") }, inverseJoinColumns = { @JoinColumn(name = "employee_id") })
    private List<Employee> employees;

    @ElementCollection
    @CollectionTable(name = "TECHNOLOGY")
    private List<String> technologies;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Project [name=").append(name).append(", department=").append(department).append(", employees=")
                .append(employees).append(", technologies=").append(technologies).append(", status=").append(status)
                .append("]");
        return builder.toString();
    }

}
