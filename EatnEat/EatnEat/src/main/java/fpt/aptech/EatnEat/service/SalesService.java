/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.entities.Orders;
import fpt.aptech.EatnEat.entities.models.SalesEmp;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class SalesService {

    @Autowired
    ViewService viewService;
    @Autowired
    OrderService orderService;
    @Autowired
    EmployeeService employeeService;

    public HashMap<String, SalesEmp> SalesEmp_findAll() {
        //tim nhan vien
        HashMap<String, SalesEmp> listEmp = new HashMap<>();

        for (Employee employee : employeeService.activeList()) {
            SalesEmp se = new SalesEmp(employee.getEmpid(),employee.getEmpname(),0,0,0);            
            listEmp.put(se.getEmpid(), se);
            List<Orders> orders = orderService.orders_emp_mounth(employee);
            if (orders != null) {
                for (Orders order : orders) {                    
                    se.setFoods(order.getQuantity());
                    se.setOrders(1);
                    se.setTotalamount(order.getTotalamount());
                    if (listEmp.get(se.getEmpid()) != null) {                        
                        SalesEmp old = listEmp.get(se.getEmpid());
                        old.setFoods(old.getFoods() + se.getFoods());
                        old.setOrders(old.getOrders() + se.getOrders());
                        old.setTotalamount(old.getTotalamount() + se.getTotalamount());
                    } else {
                        listEmp.put(se.getEmpid(), se);

                    }
                }
            } 
        }
        return listEmp;
    }

    public HashMap<String, SalesEmp> SalesEmp_findOne(Employee Empid) {
        //tim nhan vien
        HashMap<String, SalesEmp> listEmp = new HashMap<>();
        List<Orders> orders = orderService.orders_emp_mounth(Empid);

        for (Orders order : orders) {
            SalesEmp se = new SalesEmp();
            se.setEmpid(Empid.getEmpid());
            se.setEmpname(Empid.getEmpname());
            se.setFoods(order.getQuantity());
            se.setOrders(1);
            se.setTotalamount(order.getTotalamount());
            if (listEmp.get(se.getEmpid()) != null) {
                SalesEmp old = listEmp.get(se.getEmpid());
                old.setFoods(old.getFoods() + se.getFoods());
                old.setOrders(old.getOrders() + se.getOrders());
                old.setTotalamount(old.getTotalamount() + se.getTotalamount());
            } else {
                listEmp.put(se.getEmpid(), se);

            }
        }
        return listEmp;
    }

}
