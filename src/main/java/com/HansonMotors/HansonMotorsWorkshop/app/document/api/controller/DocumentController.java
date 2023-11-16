package com.HansonMotors.HansonMotorsWorkshop.app.document.api.controller;

import com.HansonMotors.HansonMotorsWorkshop.app.commons.response.ResponseDto;
import com.HansonMotors.HansonMotorsWorkshop.app.document.api.service.definitions.DocumentService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = ("${document.api.path}"))
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

  private final String fileBasePath = "D:/HansonMotors/documents/";
  private final DocumentService documentService;

  @PostMapping(value = "/add", consumes = "multipart/form-data")
  public ResponseEntity<List<String>> addDocument(
      @RequestParam(value = "documents") MultipartFile[] multipartFiles) throws Exception {
    log.info("adding documents");
    log.info("documents added");
    return new ResponseEntity<>(documentService.addDocuments(multipartFiles), HttpStatus.OK);

  }

  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity downloadDocument(
      @PathVariable String fileName) throws Exception {
    Path path = Paths.get(fileBasePath + fileName);
    Resource resource = null;
    try {
      resource = new UrlResource(path.toUri());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

  }

}
