/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;


import fpt.aptech.EatnEat.entities.models.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class SMSService {
    @Autowired
    TwiliosmsSender twiliosmsSender;
    
    public String sendSMS(SmsRequest smsRequest){
        return twiliosmsSender.sendSMS(smsRequest);
    }
}
