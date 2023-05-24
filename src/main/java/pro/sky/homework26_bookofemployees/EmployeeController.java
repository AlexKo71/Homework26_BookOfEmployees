package pro.sky.homework26_bookofemployees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public ResponseEntity<Employee> addPerson(@RequestParam(value = "firstName") String firstName,
                                              @RequestParam(value = "lastName") String lastName,
                                              @RequestParam(value = "department") int department,
                                              @RequestParam(value = "salary") int salary) {
        try {
            return ResponseEntity.ok(service.addPersons(firstName, lastName, department, salary));
        } catch (EmployeeStorageIsFullException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/remove")
    public Employee removePerson(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return service.removePersons(firstName, lastName);

    }

    @GetMapping("/find")
    public Employee findPersons(@RequestParam(value = "firstName", required = false) String firstName,
                                @RequestParam(value = "lastName", required = false) String lastName) {
        return service.findPersons(firstName, lastName);
    }

    @GetMapping("/print")
    public Collection<Employee> allPrint() {
        return service.getEmployees();
    }

}
