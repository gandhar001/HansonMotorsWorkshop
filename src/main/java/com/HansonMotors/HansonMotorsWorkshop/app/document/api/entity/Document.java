package com.HansonMotors.HansonMotorsWorkshop.app.document.api.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "mime_type")
  private String mimeType;

  @Column(name = "size")
  private long size = 0;

  @Column(name = "hash", nullable = false, unique = true)
  private String hash;
  public static final int RADIX = 16;

  public void setHash() throws NoSuchAlgorithmException {
    String transformedName = new StringBuilder().append(this.name).append(this.mimeType)
        .append(this.size).append(new Date().getTime()).toString();
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
    this.hash = new BigInteger(1, messageDigest.digest()).toString(RADIX);
  }
}
