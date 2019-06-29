package com.ufcg.psoft.ucdb.core.dto;

public class CommentDTO {
    private String author;
    private String msg;

    public CommentDTO(String author, String msg) {
        this.author = author;
        this.msg = msg;
    }

    public String getAuthor() {
        return author;
    }

    public String getMsg() {
        return msg;
    }
}
