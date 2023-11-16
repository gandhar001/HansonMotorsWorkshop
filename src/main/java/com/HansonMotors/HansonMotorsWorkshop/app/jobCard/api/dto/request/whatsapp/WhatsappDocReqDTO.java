package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WhatsappDocReqDTO {

  @JsonProperty("messaging_product")
  private String messagingProduct;
  private String to;
  private String type;
  @JsonProperty("recipient_type")
  private String recipientType;
  private Document document;

}
