package pro.sky.homework26_bookofemployees;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    public final EmployeeService employees;

    public DepartmentService(EmployeeService employees) {
        this.employees = employees;
    }

    public Employee maxSalary(int number) {
        return employees.getEmployees().stream()
                .filter(d -> d.getDepartment() == number)
                .max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }

    public Employee minSalary(int number) {
        return employees.getEmployees().stream()
                .filter(d -> (d.getDepartment() == number))
                .min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    public List<Employee> departmentList(int number) {
        return employees.getEmployees().stream()
                .filter(p -> (p.getDepartment() == number))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> listOfAllEmployees() {
        return employees.getEmployees().stream().
                collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
