package pro.sky.homework26_bookofemployees;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> allDepartment(@PathVariable int id) {
        return departmentService.departmentList(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int sumSalary(@PathVariable int id) {
        return departmentService.sumSalary(id);
    }

    @GetMapping("/{id}/salary/max")
    public Employee maxSalary(@PathVariable int id) {
        return departmentService.maxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee minSalary(@PathVariable int id) {
        return departmentService.minSalary(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> all() {
        return departmentService.listOfAllEmployees();
    }

}
