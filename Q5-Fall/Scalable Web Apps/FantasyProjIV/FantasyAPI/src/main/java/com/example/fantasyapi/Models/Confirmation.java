package com.example.fantasyapi.Models;

public class Confirmation {
    private String message;
    private String method;
    private Object object;

    public Confirmation(String message, String method, Object item) {
        this.message = message;
        this.method = method;
        this.object = item;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Confirmation{" +
                "message='" + message + '\'' +
                ", method='" + method + '\'' +
                ", object=" + object +
                '}';
    }
}
