package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.feignProxies;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp.DocUploadRes;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp.WhatsappDocReqDTO;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp.WhatsappReqDTO;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.WhatsappResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(url = "https://graph.facebook.com/v17.0/147749711744908", name = "cloud-api")
public interface WhatAppFeignClient {

  @PostMapping("/message")
  ResponseEntity<WhatsappResDTO> sendMessage(@RequestBody WhatsappReqDTO whatsappReqDTO,
      @RequestHeader("Authorization") String authCode) throws Exception;

  @PostMapping(value = "/media", consumes = {"multipart/form-data"})
  ResponseEntity<DocUploadRes> uploadMedia(@RequestPart(value = "file") MultipartFile file,
      @RequestPart(value = "type") String type,
      @RequestPart(value = "messaging_product") String messaging_product,
      @RequestHeader("Authorization") String authCode) throws Exception;

  @PostMapping(value = "/messages")
  ResponseEntity<WhatsappResDTO> sendDocument(@RequestBody WhatsappDocReqDTO whatsappReqDTO,
      @RequestHeader("Authorization") String authCode) throws Exception;
}
