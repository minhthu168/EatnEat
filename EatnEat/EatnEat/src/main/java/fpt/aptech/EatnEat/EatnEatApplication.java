package fpt.aptech.EatnEat;

import com.twilio.Twilio;
import fpt.aptech.EatnEat.config.TwilioConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EatnEatApplication {

    @Autowired
    private TwilioConfig twilioConfig;

    @PostConstruct
    public void initTwilio() {
        
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }
	public static void main(String[] args) {
		SpringApplication.run(EatnEatApplication.class, args);
	}

}
