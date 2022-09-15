/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lenovo
 */
@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {  
//    private String accountSid = "AC3495ec3b9aaee72afc31d6af1f248029";
//    private String authToken="f71d43f3b401d0206a7c047954bd66fb";
//    private String trialNumber="+12027880443";
    
    //-----------Thu2---------
    private String accountSid = "AC1fdc65aba2250c1c3ffed2fbe4351f39";
    private String authToken="8e44aa5217a276e36085d20795c541de";
    private String trialNumber="+14248422328";
    
  

}
