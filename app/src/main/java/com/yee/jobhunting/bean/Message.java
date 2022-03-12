package com.yee.jobhunting.bean;

public class Message {
    private int contactImageId;  //头像
    private String contactName; //联系人名称
    private String messageTime; //消息时间
    private String lastMessage; //最新消息

    public Message(int contactImageId, String contactName, String messageTime, String lastMessage) {
        this.contactImageId = contactImageId;
        this.contactName = contactName;
        this.messageTime = messageTime;
        this.lastMessage = lastMessage;
    }

    public int getContactImageId() {
        return contactImageId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getLastMessage() {
        return lastMessage;
    }

}
