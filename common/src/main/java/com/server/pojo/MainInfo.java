package com.server.pojo;

public class MainInfo {

    // 发件人账户
    private String sendEmailAccount;
    // 发件人密码
    private String sendEmailPassword;
    // 收件人账户
    private String receiveMailAccount;
    // 发送人姓名
    private String sendPersonName;
    // 收件人姓名
    private String receivePersonName;
    // 邮件标题
    private String mailTitle;
    // 邮件正文
    private String mailContent;


    public MainInfo() {
    }

    public MainInfo(String sendEmailAccount, String sendEmailPassword, String receiveMailAccount, String sendPersonName, String receivePersonName, String mailTitle, String mailContent) {
        this.sendEmailAccount = sendEmailAccount;
        this.sendEmailPassword = sendEmailPassword;
        this.receiveMailAccount = receiveMailAccount;
        this.sendPersonName = sendPersonName;
        this.receivePersonName = receivePersonName;
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
    }

    public String getSendEmailAccount() {
        return sendEmailAccount;
    }

    public void setSendEmailAccount(String sendEmailAccount) {
        this.sendEmailAccount = sendEmailAccount;
    }

    public String getSendEmailPassword() {
        return sendEmailPassword;
    }

    public void setSendEmailPassword(String sendEmailPassword) {
        this.sendEmailPassword = sendEmailPassword;
    }

    public String getReceiveMailAccount() {
        return receiveMailAccount;
    }

    public void setReceiveMailAccount(String receiveMailAccount) {
        this.receiveMailAccount = receiveMailAccount;
    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public String getReceivePersonName() {
        return receivePersonName;
    }

    public void setReceivePersonName(String receivePersonName) {
        this.receivePersonName = receivePersonName;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    @Override
    public String toString() {
        return "MainInfo{" +
                "sendEmailAccount='" + sendEmailAccount + '\'' +
                ", sendEmailPassword='" + sendEmailPassword + '\'' +
                ", receiveMailAccount='" + receiveMailAccount + '\'' +
                ", sendPersonName='" + sendPersonName + '\'' +
                ", receivePersonName='" + receivePersonName + '\'' +
                ", mailTitle='" + mailTitle + '\'' +
                ", mailContent='" + mailContent + '\'' +
                '}';
    }
}
