package io.pivotal.workshop.sinnpet.model;

import java.time.LocalDate;

public class SnippetRecord {

    public String id;
    public String title;
    public String code;
    public LocalDate created;
    public LocalDate modified;

    public SnippetRecord(String id, String title, String code, LocalDate created, LocalDate modified) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.created = created;
        this.modified = modified;
    }

    public SnippetRecord(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public SnippetRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "SnippetRecord{" +
                "Title='" + title + '\'' +
                ",Code='" + code + '\'' +
                '}';
    }
}