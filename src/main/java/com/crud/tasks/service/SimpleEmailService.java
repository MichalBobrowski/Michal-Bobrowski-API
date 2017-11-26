package com.crud.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
}
