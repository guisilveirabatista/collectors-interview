package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import repository.EmployeeRepository;

import java.util.List;

@RestController("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController() {
        this.employeeRepository = new EmployeeRepository();
    }

    @GetMapping
    public Employee findById(@PathVariable Long id) {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @GetMapping
    public Double getAverageSalary() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
}