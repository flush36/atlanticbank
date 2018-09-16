package br.com.bancoatlantico.atlanticbank.dto;

public class ErroDTO {

    private String msg;


    public ErroDTO (String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
