package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactsDTO {

  private String input;
  @JsonProperty("wa_id")
  private String waId;
}
