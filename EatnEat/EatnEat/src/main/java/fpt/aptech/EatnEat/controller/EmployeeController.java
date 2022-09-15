package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.service.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping("/admin/employee")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {
            List<Employee> empList = empService.activeList();
            model.addAttribute("list", empList);
            model.addAttribute("title", "List of Employees");
            model.addAttribute("emp", new Employee());
            return "admin/employee/indexEmployee";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/InactiveEmployee")
    public String inactiveEmp(Model model, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {
            List<Employee> empList = empService.InactiveList();

            model.addAttribute("list", empList);
            model.addAttribute("title", "List of Inactive Employees");
            return "admin/employee/inactiveEmployee";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/adminList")
    public String adminList(Model model, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            List<Employee> empList = empService.adminList();
            model.addAttribute("list", empList);
            model.addAttribute("title", "List of Admins");
            model.addAttribute("emp", new Employee());
            return "admin/employee/adminList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/searchEmployee")
    public String searchEmp(Model model, @RequestParam("name") String name, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            List<Employee> list = empService.activeList();
            if (!name.isEmpty()) {
                list = empService.searchByName(name);
            }

            model.addAttribute("list", list);
            model.addAttribute("title", "List of Employees Active");
            model.addAttribute("emp", new Employee());

            return "admin/employee/indexEmployee";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/createEmployee")
    public String create(Model model, Employee employee, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            employee.setRole("user");
            empService.save(employee);

            return "redirect:/admin/employee";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/addAdmin")
    public String addAdmin(Model model, Employee employee, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            employee.setRole("admin");
            empService.save(employee);

            return "redirect:/admin/adminList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/InactiveEmp")
    public String Inactive(Model model, @RequestParam("check") String[] empid, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            for (String id : empid) {
                empService.Inactive(id);
            }
            return "redirect:/admin/employee";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/ActiveEmp")
    public String Active(Model model, @RequestParam("check") String[] empid, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            for (String id : empid) {
                empService.Active(id);
            }
            return "redirect:/admin/InactiveEmployee";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/updateEmployee/{id}")
    public String update(Model model, @PathVariable("id") String empid, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            Employee emp = empService.findOne(empid);
            model.addAttribute("emp", emp);
            return "admin/employee/editEmployee";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/DoUpdateEmployee")
    public String DoUpdate(Model model, Employee employee, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            Employee emp = empService.findOne(employee.getEmpid());
            emp.setPhone(employee.getPhone());
            emp.setEmail(employee.getEmail());
            empService.save(emp);
            return "redirect:/admin/employee";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/admin/importEmployee")
    public String importEmp(Model model, HttpSession session) {
        if (session.getAttribute("ADMIN") != null) {

            return "admin/employee/importEmployee";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/importtoExcel")
    public String Doimport(Model model, @RequestParam("filePath") MultipartFile files, HttpSession session,RedirectAttributes redirectAttributes) throws IOException {
        if (session.getAttribute("ADMIN") != null) {
            if (files.isEmpty()) {
                 redirectAttributes.addFlashAttribute("message", "File import is required.");
                 return "admin/employee/importEmployee";
            }
            List<Employee> empList = new ArrayList<>();

            XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    Employee employee = new Employee();

                    XSSFRow row = worksheet.getRow(index);
                    // Integer id = (int) row.getCell(0).getNumericCellValue();

                    employee.setEmpid(row.getCell(0).getStringCellValue());
                    employee.setEmpname(row.getCell(1).getStringCellValue());
                    employee.setPhone(row.getCell(2).getStringCellValue());
                    employee.setEmail(row.getCell(3).getStringCellValue());

                    employee.setRole("user");
                    empService.save(employee);
                }
            }
            return "redirect:/admin/employee";
        } else {
            return "redirect:/login";
        }

    }

}
