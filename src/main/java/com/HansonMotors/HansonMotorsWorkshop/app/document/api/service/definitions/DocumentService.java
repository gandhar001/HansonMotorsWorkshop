package com.HansonMotors.HansonMotorsWorkshop.app.document.api.service.definitions;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

  List<String> addDocuments(MultipartFile[] multipartFiles) throws Exception;


}
