package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.service.MessageService;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chat")
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public String getMessages(ChatForm chatForm, Model model) {
        model.addAttribute("chatMessages", messageService.getAllMessages());
        return "chat";
    }

    @PostMapping()
    public String addMessage(ChatForm chatForm, Model model, Authentication authentication) {
        chatForm.setUsername(authentication.getName());
        messageService.addMessage(chatForm);
        model.addAttribute("chatMessages", messageService.getAllMessages());
        chatForm.setText("");
        chatForm.setMode("Say");
        return "chat";
    }

    @ModelAttribute("messageTypes")
    public String[] getMessageTypes() {
        return new String[] {"Say", "Shout", "Whisper"};
    }
}
