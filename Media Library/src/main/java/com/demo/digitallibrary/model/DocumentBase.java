package com.demo.digitallibrary.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@MappedSuperclass
public class DocumentBase extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "content_type", nullable = false)
    @NotNull
    private String contentType;

    @Column(name = "date_created", nullable = false)
    @NotNull
    private ZonedDateTime dateCreated;

    DocumentBase() {
    }

    DocumentBase(String name, String contentType, ZonedDateTime dateCreated) {
        this.name = name;
        this.contentType = contentType;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
