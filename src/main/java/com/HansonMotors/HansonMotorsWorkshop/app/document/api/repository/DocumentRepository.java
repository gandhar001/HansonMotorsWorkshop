package com.HansonMotors.HansonMotorsWorkshop.app.document.api.repository;

import com.HansonMotors.HansonMotorsWorkshop.app.document.api.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
