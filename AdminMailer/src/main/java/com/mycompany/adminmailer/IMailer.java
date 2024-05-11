/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.adminmailer;

import javax.mail.MessagingException;
import javax.mail.Session;



/**
 *
 * @author carlo
 */
public interface IMailer {
   public void sendPersonalizedEmail(String fromEmail, String toEmail, String subject, String body);
   public void sendEmailWithAttachment(String fromEmail, String toEmail, String subject, String body, String attachmentPath);
}
