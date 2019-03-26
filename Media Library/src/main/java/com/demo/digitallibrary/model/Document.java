package com.demo.digitallibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "documents")
public class Document extends DocumentBase {

    @Column(name = "bytes", nullable = false)
    @NotNull
    @Lob
    private byte[] bytes;

    public Document() {
    }

    public Document(String name, String contentType, ZonedDateTime dateCreated, byte[] bytes) {
        super(name, contentType, dateCreated);
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
