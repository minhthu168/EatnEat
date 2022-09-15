package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee e WHERE e.phone = :phone")
    Employee checkPhone(String phone);
    
    @Query("SELECT e FROM Employee e WHERE e.empid = :empid")
    Employee findOne(String empid);
    
     @Query("SELECT e FROM Employee e WHERE e.status = 'Active' AND e.role = 'user'")
     List<Employee> activeList();
     
     @Query("SELECT e FROM Employee e WHERE e.status = 'Inactive' AND e.role = 'user'")
     List<Employee> InactiveList();
     
     @Query("SELECT e FROM Employee e WHERE  e.role = 'admin'")
     List<Employee> adminList();
     

     //search theo emp name
    @Query("SELECT e FROM Employee e WHERE e.empname LIKE :empname AND e.status = 'Active'")
    List<Employee> findByName(String empname);
    
    
   
}
