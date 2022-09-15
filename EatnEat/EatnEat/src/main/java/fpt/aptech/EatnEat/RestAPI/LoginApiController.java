/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.RestAPI;

import fpt.aptech.EatnEat.entities.models.SmsRequest;
import fpt.aptech.EatnEat.entities.Viewemployee;
import fpt.aptech.EatnEat.repository.ViewEmployeeRepository;
import fpt.aptech.EatnEat.service.SMSService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lenovo
 */
@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    ViewEmployeeRepository repository;
    @Autowired
    SMSService smsService;

    public LoginApiController(ViewEmployeeRepository repository,SMSService smsService){
        this.repository=repository;
        this.smsService=smsService;
    }
    
    @GetMapping("/employee")
    public ResponseEntity<List<Viewemployee>> findAllEmp() {
        List<Viewemployee> list=repository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }     
    }

    
    @PostMapping("/sendOTP")
    public String sendOTP(@RequestParam("phone") String phone) {
        SmsRequest smsRequest = new SmsRequest();   
        smsRequest.setPhoneNumber("+84" + phone);
        Viewemployee employee = repository.checkPhone(smsRequest.getPhoneNumber());
        if (employee != null) {
            String otp = smsService.sendSMS(smsRequest);            
            return otp;
        }
        return null;
    }

    @GetMapping("/checkPhone/{phone}")
    public Viewemployee checkPhone(@PathVariable("phone") String phone) {
        Viewemployee employee;
        if(phone.charAt(0)=='0'){
            employee=repository.checkPhone("+84"+phone.substring(1));
        }else{
            employee = repository.checkPhone("+84"+phone);
        }   
        if (employee != null) {
            return employee;
        }
        return null;

    }
    
}
