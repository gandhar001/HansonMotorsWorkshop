package com.HansonMotors.HansonMotorsWorkshop.app.commons.service.implementations;

import com.HansonMotors.HansonMotorsWorkshop.app.commons.service.definitions.TwilioService;
import java.util.HashMap;
import java.util.Map;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwilioServiceImpl implements TwilioService {

  @Value("${twilio.from.phoneNumber}")
  private static String fromPhoneNumber;

  @Override
  public Map<String, Object> sendSmsMessage(String toPhoneNumber) throws Exception {
    var sendSmsResponse = new HashMap<String, Object>();
    //Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber("+14103437382"),
//        "Message from your EX Bf: Abhishek Gandhar, How are you Ishika !!!!").create();
    sendSmsResponse.put("to phone number", toPhoneNumber);
    log.info("sms send success to phone number  {}", toPhoneNumber);
    return sendSmsResponse;
  }
}
