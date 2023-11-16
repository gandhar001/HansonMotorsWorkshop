package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parameter {

  private String type;
  private Document document;
  private String text;
}
