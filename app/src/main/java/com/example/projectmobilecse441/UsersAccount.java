package com.example.projectmobilecse441;

public class UsersAccount {
    private int Id;
    private String username;
    private String password;
    private String fullName;
    private int accountType;
    private int totalSpent;
    private int rewardPoint;
    private int timesShopping;
    private String address;
    private int phone;

    public UsersAccount(int Id,String username, String password, String fullName, int accountType, int totalSpent, int rewardPoint, int timesShopping,String address,int phone) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.accountType = accountType;
        this.totalSpent = totalSpent;
        this.rewardPoint = rewardPoint;
        this.timesShopping = timesShopping;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getTimesShopping() {
        return timesShopping;
    }

    public void setTimesShopping(int timesShopping) {
        this.timesShopping = timesShopping;
    }

    public int getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }
}
