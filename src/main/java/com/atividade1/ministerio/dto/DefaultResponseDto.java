package com.atividade1.ministerio.dto;

public class DefaultResponseDto<T> {
    public T data;
    public String status;
    public String message;

    public DefaultResponseDto(T data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
