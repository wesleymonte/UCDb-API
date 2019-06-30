package com.ufcg.psoft.ucdb.core.dto;

public class ReplyDTO {
    private String author;
    private String msg;

    public ReplyDTO(String author, String msg) {
        this.author = author;
        this.msg = msg;
    }

    public ReplyDTO(){}

    public String getAuthor() {
        return author;
    }

    public String getMsg() {
        return msg;
    }
}
