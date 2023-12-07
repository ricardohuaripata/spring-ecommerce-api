package com.project.springecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springecommerceapi.entity.OrderItem;
import com.project.springecommerceapi.response.OrderResponse;
import com.project.springecommerceapi.service.IEmailService;

import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;
    private final Environment environment;

    // funcion asincrona
    @Override
    @Async
    public void send(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
            messageHelper.setText(content, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            mailSender.send(message);
            log.info("Verification mail sent!!");
        } catch (MessagingException e) {
            log.error("Exception occurred when sending mail", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    @Override
    public String buildEmailVerifyMail(String token, String userName) {
        String frontendRoot = environment.getProperty("app.root.frontend");
        String url = frontendRoot + "/auth/verify-email/" + token;
        return buildEmailBody(
                url,
                "Verify Email Address",
                "Hi " + userName + ", Please, click on the button below to verify your email address.",
                "Click to Verify");
    }

    @Override
    public String buildResetPasswordMail(String token) {
        String frontendRoot = environment.getProperty("app.root.frontend");
        String url = frontendRoot + "/auth/reset-password/" + token;
        return buildEmailBody(
                url,
                "Reset Your Password",
                "Please, click on the button below to get a new password.",
                "Get New Password");
    }

    private String buildEmailBody(String url, String emailBodyHeader, String emailBodyDetail, String buttonText) {
        return "<div style=\"margin: 0 auto; width: 500px; text-align: center; background: #ffffff; border-radius: 5px; border: 5px solid #FFCC03;\">"
                +
                "<h2 style=\"background: #FFCC03; padding: 15px; margin: 0; font-weight: 700; font-size: 24px; color: #000000;\">"
                + emailBodyHeader + "</h2>" +
                "<p style=\"padding: 20px; font-size: 20px; color: #000000;\">" + emailBodyDetail + "</p>" +
                "<a style=\"display: inline-block; padding: 10px 20px; margin-bottom: 30px; text-decoration: none; background: #FFCC03; font-size: 20px; border-radius: 3px; color: #000000;\" href=\" "
                + url + " \">" + buttonText + "</a>" +
                "</div>";
    }

    @Override
    public String buildOrderSuccessDetailsMail(OrderResponse orderResponse) {

        StringBuilder buildedMail = new StringBuilder();

        buildedMail.append("<div style=\"margin: 0 auto; width: 600px; text-align: center; background: #ffffff;border: 3px solid #FFCC03;\">");
        buildedMail.append("<h2 style=\"background: #FFCC03; padding: 15px; margin: 0; font-weight: 700; font-size: 24px;\">Successful Order</h2>");
        buildedMail.append("<p style=\"padding: 20px; font-size: 20px;margin-bottom: 0;margin-top: 30px;\">Hi ").append(orderResponse.getFirstname()).append(":</p>");
        buildedMail.append("<p style=\"padding: 20px; font-size: 24px;margin-bottom: 20px;margin-top: 0;\">Thanks for your purchase!</p>");

        // Order Items section
        buildedMail.append("<div>");
        buildedMail.append("<h2 style=\"background: #FFCC03; padding: 15px; margin: 0; font-weight: 700; font-size: 20px;\">Order Items</h2>");
        for (OrderItem item : orderResponse.getOrderItems()) {
            buildedMail.append("<div style=\"padding: 20px;margin-top: 20px;\">");
            buildedMail.append("<div style=\"margin-bottom: 0;\">");
            buildedMail.append("<img src=\"").append(item.getSizeColorProductVariant().getColorProductVariant().getMainImageUrl()).append("\" alt=\"").append(item.getSizeColorProductVariant().getColorProductVariant().getProduct().getTitle()).append("\" style=\"width: 100px; height: auto; margin-right: 10px; display: inline-block;\">");
            buildedMail.append("<div style=\"display: inline-block; vertical-align: top;\">");
            buildedMail.append("<p style=\"margin: 0; font-size: 16px;\"><strong>").append(item.getSizeColorProductVariant().getColorProductVariant().getProduct().getTitle()).append("</strong></p>");
            buildedMail.append("<p style=\"margin: 10px; font-size: 14px;\">Quantity: ").append(item.getQuantity()).append("</p>");
            buildedMail.append("<p style=\"margin: 10px; font-size: 14px;\">Price: ").append(item.getUnitPrice()).append("€</p>");
            buildedMail.append("</div></div></div>");
        }
        buildedMail.append("</div>");

        // Shipping Details section
        buildedMail.append("<div>");
        buildedMail.append("<h2 style=\"background: #FFCC03; padding: 15px; margin: 0; font-weight: 700; font-size: 20px;\">Shipping Details</h2>");
        buildedMail.append("<div style=\"padding: 20px;\">");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\">").append(orderResponse.getFirstname()).append(" ").append(orderResponse.getLastname()).append("</p>");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\">").append(orderResponse.getContactPhone()).append("</p>");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\">").append(orderResponse.getCountry()).append("</p>");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\">").append(orderResponse.getCity()).append("</p>");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\">").append(orderResponse.getPostalCode()).append("</p>");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\">").append(orderResponse.getAddress()).append("</p>");
        buildedMail.append("</div></div>");

        // Order Summary section
        buildedMail.append("<div>");
        buildedMail.append("<h2 style=\"background: #FFCC03; padding: 15px; margin: 0; font-weight: 700; font-size: 20px;\">Order Summary</h2>");
        buildedMail.append("<div style=\"padding: 20px;\">");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\"><strong>Order ID:</strong> ").append(orderResponse.getId()).append("</p>");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\"><strong>Order date:</strong> ").append(formatter.format(orderResponse.getOrderDate())).append("</p>");
        buildedMail.append("<p style=\"margin: 10px; font-size: 16px;\"><strong>Total price:</strong> ").append(orderResponse.getTotalAmount()).append("€</p>");
        buildedMail.append("</div></div>");

        buildedMail.append("</div>");

        return buildedMail.toString();
    }
}
