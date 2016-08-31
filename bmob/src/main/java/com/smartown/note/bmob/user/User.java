package com.smartown.note.bmob.user;

import cn.bmob.v3.BmobObject;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-31 10:29
 * <p/>
 * 描述：用户对象
 */
public class User extends BmobObject {

    private String phone;
    private String pwd;
    private String nick;
    private String name;

    public User() {
        setTableName("Users");
    }

    public User(String phone, String pwd, String nick, String name) {
        setTableName("Users");
        this.phone = phone;
        this.pwd = pwd;
        this.nick = nick;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nick='" + nick + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
