package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import fpt.aptech.EatnEat.repository.ViewRepository;

@Service
public class ViewService{

    List<Vieworderdetail> listOrders;

    @Autowired
    ViewRepository repository;

    
    public List<Vieworderdetail> findAll() {
        return listOrders = repository.findAll();
    }
    
   
    public List<Vieworderdetail> findByOrder(Integer orderid){
        return repository.findByOrder(orderid);
    }
    
}
