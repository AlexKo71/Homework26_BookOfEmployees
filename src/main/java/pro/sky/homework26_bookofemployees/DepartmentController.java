package pro.sky.homework26_bookofemployees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam(value = "departmentId") int numberDepartment) {
        return departmentService.maxSalary(numberDepartment);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam(value = "departmentId") int numberDepartment) {
        return departmentService.minSalary(numberDepartment);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public List<Employee> allDepartment(@RequestParam(value = "departmentId") int numberDepartment) {
        return departmentService.departmentList(numberDepartment);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> all() {
        return departmentService.listOfAllEmployees();
    }
}
