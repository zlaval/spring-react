package com.zlrx.springreact.springreact.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidLoginResponse {
    private String message;

    public InvalidLoginResponse(String message) {
        this.message = message;
    }
}
