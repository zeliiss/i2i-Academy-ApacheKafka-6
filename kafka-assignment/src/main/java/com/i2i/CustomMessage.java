package com.i2i;

public class CustomMessage {
    private String userId;
    private String messageContent;

    
    public CustomMessage() {}

    public CustomMessage(String userId, String messageContent) {
        this.userId = userId;
        this.messageContent = messageContent;
    }

    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMessageContent() { return messageContent; }
    public void setMessageContent(String messageContent) { this.messageContent = messageContent; }
}