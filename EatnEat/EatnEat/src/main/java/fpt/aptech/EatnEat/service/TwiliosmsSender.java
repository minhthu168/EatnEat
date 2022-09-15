/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import fpt.aptech.EatnEat.config.TwilioConfig;
import fpt.aptech.EatnEat.entities.models.SmsRequest;
import java.text.DecimalFormat;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.*;

/**
 *
 * @author lenovo
 */
@Service
public class TwiliosmsSender{
    private final static Logger LOGGER=LoggerFactory.getLogger(TwiliosmsSender.class);
    @Autowired
    private TwilioConfig twilioConfig;
    
    public String sendSMS(SmsRequest smsRequest) {      
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
           
            String otp = generatorOTP();
            String otpMessage = "Dear Customer, Your OTP is " + otp + " . User this Passcode to complete your login.";
            MessageCreator creator=Message.creator(to, from, otpMessage);
            creator.create();
            LOGGER.info("Send sms "+ smsRequest);                
            return otp;
    }
    
    //    //6 digit otp
    private String generatorOTP() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
