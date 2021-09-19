package com.example.mycollege.ui.faculty;

public class TeacherData {
    private String profile,name,email,degrees,post,faculty_of,key,experience,industry_experience;

    public TeacherData(String profile, String name, String email, String degrees, String post, String faculty_of, String key, String experience, String industry_experience) {

        this.profile = profile;
        this.name = name;
        this.email = email;
        this.degrees = degrees;
        this.post = post;
        this.faculty_of = faculty_of;
        this.key = key;
        this.experience = experience;
        this.industry_experience = industry_experience;
    }

    public String getExperience() {

        return experience;
    }

    public void setExperience(String experience) {

        this.experience = experience;
    }

    public String getIndustry_experience() {

        return industry_experience;
    }

    public void setIndustry_experience(String industry_experience) {

        this.industry_experience = industry_experience;
    }

    public String getFaculty_of() {

        return faculty_of;
    }

    public void setFaculty_of(String faculty_of) {

        this.faculty_of = faculty_of;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public TeacherData() {

    }

    public String getProfile() {

        return profile;
    }

    public void setProfile(String profile) {

        this.profile = profile;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getDegrees() {

        return degrees;
    }

    public void setDegrees(String degrees) {

        this.degrees = degrees;
    }

    public String getPost() {

        return post;
    }

    public void setPost(String post) {

        this.post = post;
    }



}
