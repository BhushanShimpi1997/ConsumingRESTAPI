package com.consume.model;

import lombok.Data;

@Data
public class Contact {

    private int contactId;
    private String name;
    private String email;
    private String mobileNum;
    private String subject;
    private String message;
    private String status;
}
