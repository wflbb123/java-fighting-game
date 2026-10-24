package domain;

import java.util.Random;

public class User {

    private String id;   //id is generated casually,so it is not included in the constructor
    private String account;  //name must be unique,and the length is between 3 and 16,and only contains letters and numbers,but cannot be numbers only.
    private String password;
    private Boolean state;

    public String getId() {
        return id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }

    // 构造方法

    public User(String account, String password) {

        this.account = account;
        this.password = password;
        setId();
        setState(true);
    }

    public void setId(){
        Random seed = new Random();
        this.id = 10000 + seed.nextInt(90000) + "";
    }
}
