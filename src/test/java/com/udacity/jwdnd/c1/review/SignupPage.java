package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "top-login-link")
    private WebElement loginLink;

    @FindBy(id = "success-msg")
    private WebElement successMsgElem;

    @FindBy(id = "error-msg")
    private WebElement errorMsgElem;

    @FindBy(id = "inputFirstName")
    private WebElement firstNameElem;

    @FindBy(id = "inputLastName")
    private WebElement lastNameElem;

    @FindBy(id = "inputUsername")
    private WebElement usernameElem;

    @FindBy(id = "inputPassword")
    private WebElement passwordElem;

    @FindBy(id = "submit-button")
    private WebElement submitBtn;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(User user) {
        firstNameElem.sendKeys(user.getFirstName());
        lastNameElem.sendKeys(user.getLastName());
        usernameElem.sendKeys(user.getUsername());
        passwordElem.sendKeys(user.getPassword());

        submitBtn.click();
    }

    public boolean hasSuccessMsg() {
        return successMsgElem != null;
    }

    public boolean hasErrorMsg() {
        return errorMsgElem != null;
    }

    public boolean hasLoginLink() {
        return loginLink != null;
    }
}
