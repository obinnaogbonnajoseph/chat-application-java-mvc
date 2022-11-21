package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageTextInput;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "chat-submit-btn")
    private WebElement chatSubmitButton;

    @FindBy(id = "logout-btn")
    private WebElement logoutButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement username;

    @FindAll({@FindBy(className = "chatMessageText")})
    private List<WebElement> messages;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void sendMessage(String msg, String msgType) {
        messageTextInput.sendKeys(msg);
        Select selectElem = new Select(messageType);
        selectElem.selectByValue(msgType);
        chatSubmitButton.click();
        // clear
        messageTextInput.clear();
        selectElem.selectByValue("Say");
    }

    public String getUsername() {
        return username.getText();
    }

    public List<String> getMessages() {
        return messages
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
