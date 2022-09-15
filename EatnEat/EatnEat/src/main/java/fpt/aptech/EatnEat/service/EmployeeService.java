package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;
   
    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee checkPhone(String phone) {
        Employee employee = repository.checkPhone(phone);
        if (employee != null) {
            return employee;
        }
        return null;
    }

    public Employee findOne(String empid) {
        return repository.findOne(empid);
    }

    public void Inactive(String empid) {
        Employee emp = repository.findOne(empid);
        if (emp != null) {
            emp.setStatus("Inactive");
            repository.save(emp);
        }
    }

    public void Active(String empid) {
        Employee emp = repository.findOne(empid);
        if (emp != null) {
            emp.setStatus("Active");
            repository.save(emp);
        }
    }

    public List<Employee> activeList() {
        return repository.activeList();
    }

    public List<Employee> InactiveList() {
        return repository.InactiveList();
    }
    public List<Employee> adminList() {
        return repository.adminList();
    }

    public List<Employee> searchByName(String empname) {
        return repository.findByName("%" + empname + "%");
    }
    public void save(Employee employee) {
        String phone = employee.getPhone();
        if (phone.charAt(0) == '0') {
            phone = phone.substring(1);//xóa ký tự đầu(so 0)
            phone = "+84" + phone; //them dau so +84 vao phone
        }
        employee.setPhone(phone);       
        employee.setStatus("Active");
        repository.save(employee);
  
    }
}
