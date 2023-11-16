package com.HansonMotors.HansonMotorsWorkshop.app.commons.service.definitions;


import java.util.Map;

public interface TwilioService {

  Map<String, Object> sendSmsMessage(String toPhoneNumber) throws Exception;

}
