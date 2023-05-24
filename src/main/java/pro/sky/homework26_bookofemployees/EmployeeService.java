package pro.sky.homework26_bookofemployees;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee addPersons(String firstName, String lastName,int department, int salary) {
        String key = firstName + " " + lastName;
        var data = new Employee(firstName, lastName, department, salary);
        if (employees.size() > 10) {
            throw new EmployeeStorageIsFullException("коллекция переполнена");
        }
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, data);
        return (Employee) employees;
    }

    public Employee removePersons(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("сотрудник не найден");
        }
        return employees.remove(key);
    }

    public Employee findPersons(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("такой сотрудник не найден");
        }
        return employees.get(key);
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

}
