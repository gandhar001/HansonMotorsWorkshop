package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Component {

  private String type;
  private List<Parameter> parameters;

}
