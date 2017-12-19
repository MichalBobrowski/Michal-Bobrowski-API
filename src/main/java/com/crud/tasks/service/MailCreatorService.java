package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://michalbobrowski.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminMail());
        context.setVariable("company_name", "Michał Bobrowski Programing Corporation");
        context.setVariable("showButton", false);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaskInformationMail(String message){
        long size = taskRepository.count();

        Context context = new Context();
        context.setVariable("message", message );
        context.setVariable("tasks_url", "https://michalbobrowski.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", "Michał Bobrowski Programing Corporation");
        context.setVariable("showButton", false);
        context.setVariable("taskNumber", size );
        return templateEngine.process("mail/information-mail.html", context);
    }

}
