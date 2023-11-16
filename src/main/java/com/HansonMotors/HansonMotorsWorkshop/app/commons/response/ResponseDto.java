package com.HansonMotors.HansonMotorsWorkshop.app.commons.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime responseTime = LocalDateTime.now();
  private Map<String, Object> responseBody;

  private final String responseStatus = "success";

  public ResponseDto(Map<String, Object> responseBody) {
    this.responseBody = responseBody;
  }

}