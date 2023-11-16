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
public class Template {

  private String name;
  private Language language;
  private List<Component> components;
}
