package com.HansonMotors.HansonMotorsWorkshop.app.user.api.controller;

import com.HansonMotors.HansonMotorsWorkshop.app.commons.response.ResponseDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.JobCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = ("${api.path}"))
public class UserController {

}