package pro.sky.homework26_bookofemployees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {
    @Mock
    EmployeeService employeeServiceMock;
    DepartmentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new DepartmentService(employeeServiceMock);
    }

    @Test
    void sumSalary() {
        List<Employee> expected =List.of(new Employee("James", "Hendrix", 1, 150000),
                new Employee("Gary", "Moore", 1, 250000),
                new Employee("Matthew", "Bellamy", 2, 350000));
        when(employeeServiceMock.getEmployees()).thenReturn(expected);

        var actual1 = 150000 + 250000;
        assertEquals(actual1, service.sumSalary(1));
    }

    @Test
    void maxSalary() {
        List<Employee> expected =List.of(new Employee("James", "Hendrix", 1, 150000),
                new Employee("Gary", "Moore", 1, 250000));
        when(employeeServiceMock.getEmployees()).thenReturn(expected);
        assertNull(service.maxSalary(3));
        assertEquals(expected.get(1),service.maxSalary(1));
    }

    @Test
    void minSalary() {
        List<Employee> expected =List.of(new Employee("James", "Hendrix", 1, 150000),
                new Employee("Gary", "Moore", 1, 250000));
        when(employeeServiceMock.getEmployees()).thenReturn(expected);
        assertNull(service.maxSalary(3));
        assertEquals(expected.get(0),service.minSalary(1));
    }

    @Test
    void departmentList() {
        List<Employee> expected =List.of(new Employee("James", "Hendrix", 1, 150000),
                new Employee("Gary", "Moore", 1, 250000),
        new Employee("Matthew", "Bellamy", 2, 350000));
        when(employeeServiceMock.getEmployees()).thenReturn(expected);

        var actual = service.departmentList(1);
        assertEquals(2,actual.size());

        List<Employee> expected1 =List.of(new Employee("James", "Hendrix", 1, 150000),
                new Employee("Gary", "Moore", 1, 250000));
        assertEquals(expected1, service.departmentList(1));

        var emptyList = service.departmentList(4);
        assertEquals(0,emptyList.size());
    }

    @Test
    void listOfAllEmployees() {
        List<Employee> expected =List.of(new Employee("James", "Hendrix", 1, 150000),
                new Employee("Gary", "Moore", 1, 250000),
                new Employee("Matthew", "Bellamy", 2, 350000));
        when(employeeServiceMock.getEmployees()).thenReturn(expected);

        Map<Integer, List<Employee>> actual = service.listOfAllEmployees();
        assertEquals(2,actual.size());
        assertEquals(2,actual.get(1).size());
        assertEquals(1,actual.get(2).size());
    }
}