package com.example.studentmgnt.model;

public class Student {
    private int sch_id;
    private String pasword;
    private String name;
    private String gender;
    private String mobile;
    private String email;
    private String district;
    private String state;
    private boolean isAdmin;

    @Override
    public String toString() {
        return "Student{" +
                "sch_id=" + sch_id +
                ", pasword='" + pasword + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public int getSch_id() {
        return sch_id;
    }

    public void setSch_id(int sch_id) {
        this.sch_id = sch_id;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Student(int sch_id, String pasword, String name, String gender, String mobile, String email, String district, String state, boolean isAdmin) {
        this.sch_id = sch_id;
        this.pasword = pasword;
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.district = district;
        this.state = state;
        this.isAdmin = isAdmin;
    }

    public Student() {

    }
}
