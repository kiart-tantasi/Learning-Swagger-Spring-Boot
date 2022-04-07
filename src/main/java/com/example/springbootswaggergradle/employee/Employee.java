package com.example.springbootswaggergradle.employee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Employee {

    @Id
    @SequenceGenerator(
            name="employee_sequence", // !
            sequenceName = "employee_sequence", // !
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence" // !
    )
    private Long id;
    private String name;
    private String position;
    private int age;

    @Transient
    private int birthYear;

    public Employee() {
    }

    public Employee(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public Employee(Long id, String name, String position, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // transient
    public int getBirthYear() {
        return LocalDate.now().getYear() - this.age;
    }
}
