package com.naukri.notification_api.service;

import com.naukri.notification_api.model.AppUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Properties;

@Service
public class CompanyService {

    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("accioshoppingwebsite@gmail.com");
        javaMailSender.setPassword("relcfdwhahhcvokv");

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", true);

        return javaMailSender;
    }

    public TemplateEngine getTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/"); // Make sure this folder exists in resources
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    public void sendInvitationMailToRecruiter(AppUser recruiter, String token) throws MessagingException {
        // To send email we need to create email content.
        String acceptLink = "http://localhost:8081/api/v1/central/company/accept-invitation/" + token;
        JavaMailSenderImpl javaMailSender = this.getJavaMailSender();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // We require this mimemessage class to set content of the mail
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(recruiter.getEmail());
        String subjectLine = "Invitation to join organization " + recruiter.getCompany().getCompanyName();
        mimeMessageHelper.setSubject(subjectLine);

        TemplateEngine templateEngine = this.getTemplateEngine();
        Context context = new Context();
        context.setVariable("recruiterName" , recruiter.getName());
        context.setVariable("companyName", recruiter.getCompany().getCompanyName());
        context.setVariable("acceptLink", acceptLink);
        String htmlContent = templateEngine.process("invite-recruiter", context);
        mimeMessageHelper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
    }

    public void sendAcceptMailNotificationToAadmin(AppUser recruiter, AppUser admin) throws MessagingException {
        JavaMailSenderImpl javaMailSender = this.getJavaMailSender();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("adminName", admin.getName());
        context.setVariable("recruiterName", recruiter.getName());
        context.setVariable("recruiterEmail", recruiter.getEmail());
        context.setVariable("recruiterPhone", recruiter.getPhoneNumber());
        mimeMessageHelper.setTo(admin.getEmail());
        mimeMessageHelper.setSubject("Invitation accepted by recruiter: " + recruiter.getName());
        String htmlContent = this.getTemplateEngine().process("accept-invitation", context);
        mimeMessageHelper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
    }

}
