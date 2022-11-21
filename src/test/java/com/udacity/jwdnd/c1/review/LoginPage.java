package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameElem;

    @FindBy(id = "inputPassword")
    private WebElement passwordElem;

    @FindBy(id = "submit-button")
    private WebElement loginBtn;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    @FindBy(id = "error-msg")
    private WebElement errorMsgElem;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameElem.sendKeys(username);
        passwordElem.sendKeys(password);
        loginBtn.click();
    }

    public boolean hasErrorMsg() {
        return errorMsgElem != null;
    }

    public boolean hasSignupLink() {
        return signupLink != null;
    }
}
