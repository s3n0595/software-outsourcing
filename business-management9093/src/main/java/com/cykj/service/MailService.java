package com.cykj.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/10 15:33
 */
@Slf4j
@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        javaMailSender.send(mailMessage);
        log.info("邮件发送成功");
    }

}
