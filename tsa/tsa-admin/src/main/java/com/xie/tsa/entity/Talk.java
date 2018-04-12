package com.xie.tsa.entity;

import com.hd.sfw.core.model.BaseModel;

public class Talk<T> extends BaseModel{
    private String status;
    private T data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Talk(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Talk(String status, String message, T data) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
