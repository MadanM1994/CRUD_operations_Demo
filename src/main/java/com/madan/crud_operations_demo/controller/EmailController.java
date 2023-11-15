//package com.madan.crud_operations_demo.controller;
//
//import com.madan.crud_operations_demo.email.EmailDetails;
//import com.madan.crud_operations_demo.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("email")
//public class EmailController {
//
//    @Autowired
//    private final EmailService emailService;
//
//    public EmailController(EmailService emailService){
//        this.emailService = emailService;
//    }
//
//    @PostMapping("send")
//    public ResponseEntity<String> sendEmail (@RequestBody EmailDetails emailDetails){
//        return ResponseEntity.ok(emailService.sendEmail(emailDetails));
//    }
//
//}
