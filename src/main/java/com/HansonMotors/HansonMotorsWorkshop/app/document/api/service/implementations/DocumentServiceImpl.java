package com.HansonMotors.HansonMotorsWorkshop.app.document.api.service.implementations;

import com.HansonMotors.HansonMotorsWorkshop.app.document.api.entity.Document;
import com.HansonMotors.HansonMotorsWorkshop.app.document.api.property.DocumentStorageProperty;
import com.HansonMotors.HansonMotorsWorkshop.app.document.api.repository.DocumentRepository;
import com.HansonMotors.HansonMotorsWorkshop.app.document.api.service.definitions.DocumentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class DocumentServiceImpl implements DocumentService {

  private final String fileBasePath = "D:/HansonMotors/documents";
  @Autowired
  private DocumentRepository documentRepository;
  private final Path docStorageLocation;

  @Autowired
  public DocumentServiceImpl(DocumentStorageProperty documentStorageProperty) throws IOException {
    this.docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory())
        .toAbsolutePath().normalize();
    Files.createDirectories(this.docStorageLocation);
  }

  @Override
  public List<String> addDocuments(MultipartFile[] multipartFiles) throws Exception {
    List<String> result = new ArrayList<>();
    for (MultipartFile multipartFile : multipartFiles) {
      result.add(createAndSaveDocument(multipartFile));
    }
    return result;
  }

  private String createAndSaveDocument(MultipartFile multipartFile)
      throws NoSuchAlgorithmException, IOException {
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    var document = Document.builder().name(fileName)
        .size(multipartFile.getSize()).mimeType(multipartFile.getContentType()).build();
    document.setHash();
    var resultUri = storeDocument(multipartFile, fileName);
    documentRepository.save(document);
    return resultUri;
  }

  //  private void storeDocument(MultipartFile multipartFile, String hash) throws IOException {
//    Path targetLocation = this.docStorageLocation.resolve(hash);
//    Files.copy(multipartFile.getInputStream(), targetLocation);
//  }
  private String storeDocument(MultipartFile multipartFile, String fileName) throws IOException {
    Path targetLocation = Paths.get(fileBasePath, fileName);
    Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/dev/document/api/v1/download/").path(fileName).toUriString();
    return fileDownloadURI;
  }


}

