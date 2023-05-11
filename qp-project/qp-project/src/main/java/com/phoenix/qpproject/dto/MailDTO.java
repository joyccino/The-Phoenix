package com.phoenix.qpproject.dto;

import lombok.Data;

@Data
public class MailDTO {
    private String address;
    private String title;
    private String content;
    private String theme;
}
