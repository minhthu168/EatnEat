package fpt.aptech.EatnEat.RestAPI;

import fpt.aptech.EatnEat.entities.*;
import fpt.aptech.EatnEat.entities.models.ViewOrders;
import fpt.aptech.EatnEat.repository.ViewOrdersRepository;
import fpt.aptech.EatnEat.repository.ViewRepository;
import fpt.aptech.EatnEat.service.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderApiController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    FoodService foodService;
    @Autowired
    ViewOrdersRepository viewOrdersRepository;
    @Autowired
    ViewRepository viewRepository;

    @GetMapping("/orderList/{empid}")
    public ResponseEntity<List<ViewOrders>> findAllOrders(@PathVariable("empid") String empid) {
        List<Orders> list = orderService.findAllOrderByEmpid(employeeService.findOne(empid));
        List<ViewOrders> listOrders = new ArrayList<>();
        for (Orders o : list) {
            ViewOrders order = new ViewOrders(o.getOrderid(), empid, null, o.getQuantity(), o.getTotalamount(), o.getOrderdate());
            String food = "";
            for (Orderdetail od : o.getOrderdetailList()) {
                food = food + "+" + od.getFoodid().getFoodname();
            }
            order.setFood(food.substring(1));
            listOrders.add(order);
         
        }
        if (listOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listOrders, HttpStatus.OK);
        }
    }

    @GetMapping("/orderDetailList/{orderid}")
    public ResponseEntity<List<Vieworderdetail>> findAllOrderDetail(@PathVariable("orderid") int orderid) {
        Orders order = orderService.findOne(orderid);
        List<Vieworderdetail> list = viewRepository.findByOrder(orderid);

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/saveOrder")
    public int saveOrder(@RequestParam("empid") String empid, @RequestParam("quantity") int quantity, @RequestParam("total") int total) {
        Employee emp = employeeService.findOne(empid);
        Orders o = orderService.findByEmpToDay(emp);
        //kiem tra xem employee co dat hang cho hom nay chua ,neu dat roi thi check xem tong tien co vuot qua 35.000 khong
        //neu khong vuot qua va food muon dat tiep + total truoc do ko qua 35000 thi cap nhat order them food moi dat
        if (o != null) {
            if (o.getTotalamount() < 35000) {
                if (total + o.getTotalamount() <= 35000) {
                    o.setQuantity(o.getQuantity() + quantity);
                    o.setTotalamount(o.getTotalamount() + total);
                    return o.getOrderid();
                } else {
                    //dat roi va < 35.000 loi 1
                    return -1;
                }
                //dat roi va >35.000 loi 2
            } else {
                return -2;
            }
            //neu chua dat hom nay thi cho dat moi order
        } else {
            Orders order = new Orders();
            order.setEmpid(emp);
            order.setQuantity(quantity);
            order.setTotalamount(total);
            order.setOrderdate(new Date());
            return orderService.create(order).getOrderid();
        }

    }

    @PostMapping("/saveOrderDetail")
    public Integer saveOrderDetail(@RequestParam("orderid") int orderid, @RequestParam("foodid") int foodid, @RequestParam("quantity") int quantity) {
        Orderdetail orderdetail = new Orderdetail();
        Orders o = orderService.findOne(orderid);
        orderdetail.setOrderid(o);
        Food f = foodService.findOneFood(foodid);
        orderdetail.setFoodid(f);
        orderdetail.setPrice(f.getPrice());
        orderdetail.setQuantity(quantity);
        orderdetail.setTotalamount(orderdetail.getQuantity() * orderdetail.getPrice());
        orderdetail.setDate(new Date());
        orderDetailService.saveOrderDetail(orderdetail);
        return 1;
    }
}
