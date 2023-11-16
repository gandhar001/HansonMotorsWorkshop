package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WhatsappResDTO {

  @JsonProperty("messaging_product")
  private String messagingProduct;
  private List<ContactsDTO> contacts;
  private List<MessagesDTO> messages;
}
