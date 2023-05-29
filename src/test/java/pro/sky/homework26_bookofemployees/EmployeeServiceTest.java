package pro.sky.homework26_bookofemployees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceTest {

    EmployeeService service = new EmployeeService();

    @Test
    void addPersons() {
        var expected = new Employee("James", "Hendrix", 1, 150000);
        var actual = service.addPersons("James", "Hendrix", 1, 150000);
        assertEquals(expected, actual);
    }

    @Test
    void alreadyAdded() {
        service.addPersons("James", "Hendrix", 1, 150000);
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                service.addPersons("James", "Hendrix", 1, 150000));
    }

    @Test
    void storageIsFull() {
        service.addPersons("James", "Hendrix", 1, 150000);
        service.addPersons("James1", "Hendrix1", 1, 160000);
        service.addPersons("James2", "Hendrix2", 1, 170000);
        service.addPersons("James3", "Hendrix3", 1, 180000);
        service.addPersons("James4", "Hendrix4", 1, 190000);
        service.addPersons("James5", "Hendrix5", 1, 200000);
        service.addPersons("James6", "Hendrix6", 1, 150000);
        service.addPersons("James7", "Hendrix7", 1, 150000);
        service.addPersons("James8", "Hendrix8", 1, 150000);
        service.addPersons("James9", "Hendrix9", 1, 150000);
        service.addPersons("James10", "Hendrix10", 1, 150000);

        assertThrows(EmployeeStorageIsFullException.class, () ->
                service.addPersons("Gary", "Moore", 2, 250000));
    }

    @Test
    void removePersons() {
        service.addPersons("David", "Gilmour", 1, 300000);
        service.removePersons("David", "Gilmour");
        assertEquals(0, service.getEmployees().size());
        assertThrows(EmployeeNotFoundException.class, () -> service.findPersons("Matthew", "Bellamy"));
    }

    @Test
    void findPersons() {
        var findPerson = new Employee("Matthew", "Bellamy", 2, 350000);
        service.addPersons("Matthew", "Bellamy", 2, 350000);
        assertEquals(findPerson,service.findPersons("Matthew", "Bellamy"));

        assertThrows(EmployeeNotFoundException.class, () -> service.findPersons("Matthew1", "Bellamy1"));
    }

    @Test
    void getEmployees() {
        service.addPersons("Mark", "Knopfler", 2, 200000);
        service.addPersons("David", "Gilmour", 1, 300000);
        Collection<Employee> actual = service.getEmployees();

        assertEquals(2, actual.size());

        List<Employee> expected = List.of(new Employee("Mark", "Knopfler", 2, 200000),
                new Employee("David", "Gilmour", 1, 300000));

        assertIterableEquals(expected, actual);

    }

    @Test
    void getEmployeesEmpty() {
        assertEquals(0, service.getEmployees().size());
    }
}