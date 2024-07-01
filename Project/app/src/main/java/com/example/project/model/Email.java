package com.example.project.model;

import android.os.AsyncTask;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email extends AsyncTask<Void, Void, Void> {
    final String from = "vovuminhtam135@gmail.com";
    final String password = "sklv qqir bggx zpbc";
    private String toEmail;
    private String subject;
    private String body;

    public Email(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    @Override
    protected Void doInBackground(Void... voids) {


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(toEmail, false)
            );
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(body);

            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
