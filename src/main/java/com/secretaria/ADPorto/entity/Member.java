package com.secretaria.ADPorto.entity;


import java.util.Map;

public class  Member {
    private boolean active;
    private String congregationName;
    private String memberName;
    private String fatherMemberName;
    private String motherMemberName;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCongregationName() {
        return congregationName;
    }

    public void setCongregationName(String congregationName) {
        this.congregationName = congregationName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getFatherMemberName() {
        return fatherMemberName;
    }

    public void setFatherMemberName(String fatherMemberName) {
        this.fatherMemberName = fatherMemberName;
    }

    public String getMotherMemberName() {
        return motherMemberName;
    }

    public void setMotherMemberName(String motherMemberName) {
        this.motherMemberName = motherMemberName;
    }

}

