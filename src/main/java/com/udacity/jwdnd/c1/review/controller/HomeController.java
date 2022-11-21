package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.MessageForm;
import com.udacity.jwdnd.c1.review.service.MessageListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final MessageListService messageService;

    public HomeController(MessageListService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public String getHomePage(MessageForm newMessage, Model model) {
        model.addAttribute("greetings", messageService.getMessages());
        return "home";
    }

    @PostMapping()
    public String addMessage(MessageForm newMessage, Model model) {
        this.messageService.addMessage(newMessage.getText());
        model.addAttribute("greetings", messageService.getMessages());
        newMessage.setText("");
        return "home";
    }
}
