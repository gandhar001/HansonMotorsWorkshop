package com.HansonMotors.HansonMotorsWorkshop.app.user.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

  private long id;

  private String username;

  private String password;

  private String firstName;

  private String lastName;

  private String email;
}
