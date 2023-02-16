package com.example.androidfirebasefirst;

public class DataHolder {
    private String name, Duration,branch;

    public DataHolder(String name, String course, String branch) {
        this.name = name;
        this.Duration = course;
        this.branch = branch;
    }

    public DataHolder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return Duration;
    }

    public String getBranch() {
        return branch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(String duration) {
        this.Duration = duration;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "DataHolder{" +
                "name='" + name + '\'' +
                ", course='" + Duration + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

}
