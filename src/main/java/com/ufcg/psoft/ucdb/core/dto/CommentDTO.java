package com.ufcg.psoft.ucdb.core.dto;

public class CommentDTO {
    private String msg;

    public CommentDTO(String author, String msg) {
        this.msg = msg;
    }

    public CommentDTO(){}

    public String getMsg() {
        return msg;
    }
}
