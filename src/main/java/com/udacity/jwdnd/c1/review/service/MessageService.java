package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageService {
    
    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    private List<String> bannedWords;

    public void addMessage(ChatForm chatForm) {
        if(!bannedWords.contains(chatForm.getText())) {
            var newMessage = new ChatMessage();
            newMessage.setUsername(chatForm.getUsername());
            switch (chatForm.getMode()) {
                case "Say" -> newMessage.setMessageText(chatForm.getText());
                case "Shout" -> newMessage.setMessageText(chatForm.getText().toUpperCase());
                case "Whisper" -> newMessage.setMessageText(chatForm.getText().toLowerCase());
            }
            messageMapper.insertMessage(newMessage);
        }
    }

    public List<ChatMessage> getAllMessages() {
        return new ArrayList<>(messageMapper.getAllMessages());
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Message service");
        bannedWords = new ArrayList<>(Arrays.asList("Fuck", "Die"));
    }
}
