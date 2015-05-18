package com.cloudtroopers.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cloudtroopers.jpa.entity.Address;
import com.cloudtroopers.jpa.entity.Department;
import com.cloudtroopers.jpa.entity.Employee;
import com.cloudtroopers.jpa.entity.Project;
import com.cloudtroopers.jpa.entity.ProjectStatus;
import com.cloudtroopers.jpa.entity.inheritance.Bike;
import com.cloudtroopers.jpa.entity.inheritance.Car;
import com.cloudtroopers.jpa.entity.inheritance.Truck;

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

        // new JPA().doTheProjects();
        new JPA().doTheInheritance();

    }

    public void doTheProjects() {
        // CriteriaBuilder criteriaBuilder = jpa.getEntityManager().getCriteriaBuilder();
        // CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        // Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        // TypedQuery<Employee> query = jpa.getEntityManager().createQuery(criteriaQuery);
        // query.getResultList();

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
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address("Eroilor", "Cluj-Napoca"));
        addresses.add(new Address("M. Piuariu", "Cluj-Napoca"));
        employee.setAddresses(addresses);
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);

        getEntityManager().getTransaction().begin();
        getEntityManager().persist(employee);
        project.setEmployees(employees);
        getEntityManager().persist(department);
        getEntityManager().persist(project);
        getEntityManager().getTransaction().commit();

        TypedQuery<Project> query = getEntityManager().createNamedQuery(Project.FIND_ALL, Project.class);
        List<Project> projects = query.getResultList();
        for (Project p : projects) {
            System.out.println(p);
        }

        getEntityManager().close();
        getEntityManagerFactory().close();
    }

    public void doTheInheritance() {
        Bike cbr1000rr = new Bike();
        cbr1000rr.setManufacturer("honda");
        cbr1000rr.setNoOfpassengers(1);
        cbr1000rr.setSaddleHeight(30);
        getEntityManager().persist(cbr1000rr);

        Car aventador = new Car();
        aventador.setManufacturer("lamborghini");
        aventador.setNoOfDoors(2);
        aventador.setNoOfpassengers(2);
        getEntityManager().persist(aventador);

        Truck truck = new Truck();
        truck.setLoadCapacity(1000);
        truck.setManufacturer("volvo");
        truck.setNoOfContainers(2);
        getEntityManager().persist(truck);

        getEntityManager().close();
        getEntityManagerFactory().close();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
