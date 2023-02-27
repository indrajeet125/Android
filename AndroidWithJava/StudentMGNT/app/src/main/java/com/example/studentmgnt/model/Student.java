package com.example.studentmgnt.model;

public class Student {
    private int  sch_id;
    private String name;
    private String gender;
    private String mobile;
    private String email;

    private String district;
    private String state;
    private boolean isAdmin;

    public int getSch_id() {
        return sch_id;
    }

    public void setSch_id(int sch_id) {
        this.sch_id = sch_id;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public  Student(){

}

    public Student(int sch_id, String name, String gender, String mobile, String email, String district, String state, boolean isAdmin) {
        this.sch_id = sch_id;
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.district = district;
        this.state = state;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sch_id=" + sch_id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public void setState(String state) {
        this.state = state;
    }


}
