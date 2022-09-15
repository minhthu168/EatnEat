package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.*;
import fpt.aptech.EatnEat.service.*;
import java.time.LocalTime;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    IngredientService ingredientService;

    @GetMapping("list")
    public String list(HttpSession session, Model model) {
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        if (emp != null) {
            if (session.getAttribute("cart") != null) {
                model.addAttribute("total", totalAmount(session));
                model.addAttribute("count", totalCount(session));
            } else {
                return "cart";
            }
        } else {
            return "redirect:/login";
        }
        return "cart";
    }

    @GetMapping("add/{foodid}")
    public String add(@PathVariable("foodid") int foodid, HttpSession session, RedirectAttributes redirectAttributes) {
        Food food = foodService.findOneFood(foodid);
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        if (emp != null) {
            if (session.getAttribute("cart") == null) {//neu cart trong rong
                List<Item> cart = new ArrayList<Item>();//ds cac mat hang(food) trong gio
                //them moi item(=food) vao gio
                cart.add(new Item(food, food.getFoodname(), food.getPrice(), 1));
                session.setAttribute("cart", cart);//luu session ds cac mat hang(food) trong gio
            } else {//nguoc lai cart co chua item(food)
                //lay ra session "cart" chua ds cac item(food) trong gio
                List<Item> cart = (List<Item>) session.getAttribute("cart");
                int index = isExists(foodid, cart);
                if (index == -1) {//foodid muon add chua ton tai trong cart
                    if (totalAmount(session) >= 35000) {//cart du 35k
                        redirectAttributes.addFlashAttribute("message", "Total price is full 35000vnd. You can't order more!");
                    } else {//cart chua du 35k
                        if (food.getPrice() <= 35000 - totalAmount(session)) {
                            cart.add(new Item(food, food.getFoodname(), food.getPrice(), 1));
                        } else {
                            redirectAttributes.addFlashAttribute("message", "Total price will be over 35000vnd if you add more " + food.getFoodname());
}
                    }
                } else {//nguoc lai foodid muon add da ton tai trong cart
                    if (totalAmount(session) >= 35000) {//cart du 35k
                        redirectAttributes.addFlashAttribute("message", "Total price is full 35000vnd. You can't order more!");
                    } else {//cart chua du 35k
                        if (food.getPrice() <= 35000 - totalAmount(session)) {
                            int quantity = cart.get(index).getQuantity() + 1;//quantity cua foodid +1
                            cart.get(index).setQuantity(quantity);
                            if (totalAmount(session) > 35000) {
                                cart.get(index).setQuantity(quantity - 1);
                                redirectAttributes.addFlashAttribute("message", "Total price will be over 35000vnd if you increase the quantity " + food.getFoodname());
                            }
                        }
                    }
                }
                session.setAttribute("cart", cart);
            }
        } else {
            return "redirect:/login";
        }
        return "redirect:/cart/list";
    }

    private int isExists(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getFood().getFoodid() == id) {
                return i;//tra ve vi tri thu i cua item(food) trong gio co foodid = id dang xet
            }
        }
        return -1;
    }

    @GetMapping("remove/{foodid}")
    public String remove(Model model, @PathVariable("foodid") int foodid, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
//        int index = isExists(foodid, cart);
//        cart.remove(index);
        cart.removeIf(item -> item.getFood().getFoodid() == foodid);
        session.setAttribute("cart", cart);
        return "redirect:/cart/list";
    }

    @GetMapping("clear")
    public String clear(HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        cart.removeAll(cart);
        session.setAttribute("cart", cart);
        return "redirect:/cart/list";
    }

    @PostMapping("update")
    public String update(Model model, HttpSession session, @RequestParam("foodid") Integer foodid, @RequestParam("quantity") Integer quantity, RedirectAttributes redirectAttributes) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getFood().getFoodid() == foodid) {
                cart.get(i).setQuantity(quantity);
                if (totalAmount(session) > 35000) {
                    cart.get(i).setQuantity(quantity - 1);
                    redirectAttributes.addFlashAttribute("message", "Total price will be over 35000vnd if you increase the quantity " + cart.get(i).getFood().getFoodname());
                }
                if (cart.get(i).getQuantity() < 1) {
cart.get(i).setQuantity(1);
                    redirectAttributes.addFlashAttribute("message", "Minimun quantity is 1 !");
                } else if (cart.get(i).getQuantity() > 2) {
                    cart.get(i).setQuantity(2);
                    redirectAttributes.addFlashAttribute("message", "Maximun quantity is 2 !");
                }
                break;
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/list";
    }

    @GetMapping("history")
    public String orderHistory(HttpSession session, Model model) {
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        if (emp != null) {//neu employee da login
            List<Orders> list = orderService.findAllOrderByEmpid(emp);
            model.addAttribute("LIST", list);
            return "history";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("detailhistory-{orderid}")
    public String detailHistory(@PathVariable("orderid") int orderid, Model model) {
        Orders order = orderService.findOne(orderid);
        model.addAttribute("DETAIL", orderDetailService.detail(order));
        model.addAttribute("order", order);
        return "detail";
    }

    private int totalAmount(HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int s = 0;
        for (Item item : cart) {
            s += item.getQuantity() * item.getFood().getPrice();
        }
        return s;
    }

    private int totalCount(HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int c = 0;
        for (Item item : cart) {
            c += item.getQuantity();
        }
        return c;
    }

    private int totalCountOfToday(HttpSession session) {
        int count;
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        Orders todayOrder = orderService.findByEmpToDay(emp);
        return count = todayOrder.getQuantity();
    }

    private int totalAmountOfToday(HttpSession session) {
        int sum;
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        Orders todayOrder = orderService.findByEmpToDay(emp);
        return sum = todayOrder.getTotalamount();
    }

    @RequestMapping(value = "order")
    public String checkout(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        Orders todayOrder = orderService.findByEmpToDay(emp);
        if (emp != null) {
            if (session.getAttribute("cart") == null) {
                redirectAttributes.addFlashAttribute("message", "You don't choose any item !");
                return "redirect:/cart/list";
            } else if (LocalTime.now().getHour() > 16) {
                redirectAttributes.addFlashAttribute("message", "It's over 10 o'clock! The time for ordering food passed. See you tomorrow");
                return "redirect:/cart/list";
//neu tim thay order trong ngay cua employee dang nhap(employee da tao order trong hom nay) 
            } else if (todayOrder != null) {
                //neu tong tien cart muon them tiep <= MaxTotalAmount - tong tien cua order da tao trong hom nay 
                if (totalAmount(session) <= 35000 - totalAmountOfToday(session)) {
                    //Add new order
                    // Orders order = new Orders();
                    todayOrder.setEmpid(emp);
                    todayOrder.setQuantity(totalCount(session) + totalCountOfToday(session));
                    todayOrder.setTotalamount(totalAmount(session) + totalAmountOfToday(session));
                    todayOrder.setOrderdate(new Date());
                    Orders o = orderService.create(todayOrder);

                    //Add new orderDetail
                    List<Item> cart = (List<Item>) session.getAttribute("cart");
                    for (Item item : cart) {
                        Orderdetail orderdetail = new Orderdetail();
                        orderdetail.setOrderid(todayOrder);
                        orderdetail.setFoodid(item.getFood());
                        orderdetail.setPrice(item.getFood().getPrice());
                        orderdetail.setQuantity(item.getQuantity());
                        orderdetail.setTotalamount(item.getFood().getPrice() * item.getQuantity());
                        orderdetail.setDate(new Date());
                        orderDetailService.saveOrderDetail(orderdetail);
                    }
                    //Delete cart
                    session.removeAttribute("cart");
                    model.addAttribute("order", o);//doi tuong order moi tao
                    model.addAttribute("detail", orderDetailService.detail(o));//hien thi ds Orderdetail theo order moi tao 
                } else {
                    redirectAttributes.addFlashAttribute("message", "Your balance isn't enough to add more food into your todayOrder !");
                    return "redirect:/cart/list";
                }
            } else {
                //neu ko tim thay order trong ngay cua employee dang nhap(employee chua tao order trong hom nay) 
                //Add new order
                Orders order = new Orders();
                order.setEmpid(emp);
                order.setQuantity(totalCount(session));
                order.setTotalamount((int) totalAmount(session));
                order.setOrderdate(new Date());
                Orders o = orderService.create(order);
                //Add new orderDetail
                List<Item> cart = (List<Item>) session.getAttribute("cart");
                for (Item item : cart) {
                    Orderdetail orderdetail = new Orderdetail();
                    orderdetail.setOrderid(order);
                    orderdetail.setFoodid(item.getFood());
                    orderdetail.setPrice(item.getFood().getPrice());
                    orderdetail.setQuantity(item.getQuantity());
orderdetail.setTotalamount(item.getFood().getPrice() * item.getQuantity());
                    orderdetail.setDate(new Date());
                    orderDetailService.saveOrderDetail(orderdetail);
                }
                //Delete cart
                session.removeAttribute("cart");
                model.addAttribute("order", o);//doi tuong order moi tao
                model.addAttribute("detail", orderDetailService.detail(o));//hien thi ds Orderdetail theo order moi tao               
            }
        } else {
            return "redirect:/login";
        }
        return "thanks";
    }
}



