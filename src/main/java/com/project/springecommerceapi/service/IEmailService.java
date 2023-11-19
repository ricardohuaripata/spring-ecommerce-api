package com.project.springecommerceapi.service;

public interface IEmailService {
    void send(String to, String subject, String content);

    String buildEmailVerifyMail(String token, String name);

    String buildResetPasswordMail(String token);

}
