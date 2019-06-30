package com.ufcg.psoft.ucdb.core.dto;

public class ReplyDTO {
    private String msg;

    public ReplyDTO(String author, String msg) {
        this.msg = msg;
    }

    public ReplyDTO(){}

    public String getMsg() {
        return msg;
    }
}
