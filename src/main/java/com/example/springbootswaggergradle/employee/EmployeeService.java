package com.example.springbootswaggergradle.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service // comment for spring data rest to work
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("employee with id " + id + " cannot be found."));
        return employee;
    }

    public void addEmployee(Employee employee) {
        if (employee.getName() == null || employee.getPosition() == null || employee.getAge() < 1) throw new IllegalStateException("Missing some information");
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                        .orElseThrow(() -> new IllegalStateException("no employee with id " + id.toString() + " found"));
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void updateEmployee(Long id, Employee employee) {
        Employee employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("no employee with id " + id.toString() + " found"));

        String newName = employee.getName();
        if (newName != null && newName != employeeToUpdate.getName()) {
            employeeToUpdate.setName(newName);
        }

        String newPosition = employee.getPosition();
        if (newPosition != null && newPosition != employeeToUpdate.getPosition()) {
            employeeToUpdate.setPosition(newPosition);
        }

        int newAge = employee.getAge();
        if (newAge > 0 && newAge != employeeToUpdate.getAge()) {
            employeeToUpdate.setAge(newAge);
        }
    }

}
