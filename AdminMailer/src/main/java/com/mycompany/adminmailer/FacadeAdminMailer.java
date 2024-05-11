/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adminmailer;

import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * @author carlo
 */
public class FacadeAdminMailer implements IMailer {
    
    private ControlMailer control;

    public FacadeAdminMailer() {
        this.control = new ControlMailer();
    }

    @Override
    public void sendPersonalizedEmail(String fromEmail, String toEmail, String subject, String body) {
        this.control.sendPersonalizedEmail(fromEmail, toEmail, subject, body);
    }
    
    @Override
    public void sendEmailWithAttachment(String fromEmail, String toEmail, String subject, String body, String attachmentPath) {
        this.control.sendEmailWithAttachment(fromEmail, toEmail, subject, body, attachmentPath);
    }
    
    
}
