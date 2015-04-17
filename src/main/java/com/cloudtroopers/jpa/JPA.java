package com.cloudtroopers.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cloudtroopers.jpa.entity.Department;
import com.cloudtroopers.jpa.entity.Employee;
import com.cloudtroopers.jpa.entity.Project;
import com.cloudtroopers.jpa.entity.ProjectStatus;

/**
 * 
 * @author Radu Cosma <radu.cosma@cloudtroopers.com> 
 *
 */
public class JPA {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public JPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpaPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {

        JPA jpa = new JPA();

        Department department = new Department();
        department.setName("Java Department");
        
        Project project = new Project();
        project.setName("Java Project");
        project.setStatus(ProjectStatus.IN_PROGRESS);
        List<String> technologies = new ArrayList<String>();
        technologies.add("JavaEE");
        technologies.add("JPA");
        technologies.add("MySQL");
        project.setTechnologies(technologies);
        project.setDepartment(department);
        
        Employee employee = new Employee();
        employee.setFirstName("Radu");
        employee.setLastName("Cosma");
        employee.setDepartment(department);
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        
        jpa.getEntityManager().getTransaction().begin();
        jpa.getEntityManager().persist(employee);
        project.setEmployees(employees);
        jpa.getEntityManager().persist(department);
        jpa.getEntityManager().persist(project);
        jpa.getEntityManager().getTransaction().commit();
       

        TypedQuery<Project> query = jpa.getEntityManager().createNamedQuery(Project.FIND_ALL, Project.class);
        List<Project> projects = query.getResultList();
        for (Project p : projects) {
            System.out.println(p);
        }

        jpa.getEntityManager().close();
        jpa.getEntityManagerFactory().close();

    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
