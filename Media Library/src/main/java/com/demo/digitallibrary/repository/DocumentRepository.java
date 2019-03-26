package com.demo.digitallibrary.repository;

import com.demo.digitallibrary.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
