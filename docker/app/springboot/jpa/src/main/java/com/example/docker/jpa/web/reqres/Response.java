package com.example.docker.jpa.web.reqres;

import lombok.Getter;

@Getter
public class Response {

    private String name;

    public static Response of(String name) {
        Response response = new Response();
        response.name = name;
        return response;
    }
}
