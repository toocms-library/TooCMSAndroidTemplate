package com.toocms.template.config;

import com.toocms.frame.config.IUser;

/**
 * Author：Zero
 * Date：2017/5/3 16:10
 *
 * @version v2.0
 */

public class User implements IUser {

    /**
     * member_id : 3
     * account : 15822829206
     * nickname : 15822829206
     * cover : http://xsd-img.toocms.com//Uploads/2016-12-20/58589bf2a7878.jpg
     * password_pay : 1
     */

    private String member_id;
    private String account;
    private String nickname;
    private String cover;
    private String password_pay;
    private String balance;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPassword_pay() {
        return password_pay;
    }

    public void setPassword_pay(String password_pay) {
        this.password_pay = password_pay;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
