package com.udacity.jwdnd.c1.review.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class MessageListService {

    private List<String> messages;

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(this.messages);
    }

    @PostConstruct
    private void postConstruct() {
        this.messages = new ArrayList<>();
    }
}
