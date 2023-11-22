package com.project.springecommerceapi.service;

import com.project.springecommerceapi.response.OrderResponse;

public interface IEmailService {
    void send(String to, String subject, String content);

    String buildEmailVerifyMail(String token, String name);

    String buildResetPasswordMail(String token);

    String buildOrderSuccessDetailsMail(OrderResponse orderResponse);

}
