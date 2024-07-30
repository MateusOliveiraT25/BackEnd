package com.example.feedback.controller;

import com.example.feedback.model.ContactMessage;
import com.example.feedback.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        return "contact";
    }

    @PostMapping("/submitContact")
    public String submitContact(@RequestParam String name, 
                                @RequestParam String email, 
                                @RequestParam String message, 
                                Model model) {
        // Salvar mensagem no banco de dados
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(name);
        contactMessage.setEmail(email);
        contactMessage.setMessage(message);
        contactMessage.setSubmittedAt(LocalDateTime.now());
        contactMessageRepository.save(contactMessage);

        // Enviar email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("suporte@example.com");
        mailMessage.setSubject("Nova mensagem de contato");
        mailMessage.setText("Nome: " + name + "\nEmail: " + email + "\nMensagem: " + message);
        mailSender.send(mailMessage);

        model.addAttribute("successMessage", "Mensagem enviada com sucesso!");
        return "contact";
    }
}
