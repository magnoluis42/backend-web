package com.project.appjava.exception;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(){
        super("../../main/resources/templates/alert_email.html");

    }
}
