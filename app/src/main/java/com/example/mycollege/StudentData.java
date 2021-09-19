package com.example.mycollege;

public class StudentData {
    private String profile,name,email,phone,rollno,dateofbirth,department,year,section,key,uploaded_Date;

    public StudentData(String profile, String name, String email, String phone, String rollno, String dateofbirth, String department, String year, String section, String key, String uploaded_Date) {

        this.profile = profile;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rollno = rollno;
        this.dateofbirth = dateofbirth;
        this.department = department;
        this.year = year;
        this.section = section;
        this.key = key;
        this.uploaded_Date = uploaded_Date;
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

    public StudentData() {

    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getRollno() {

        return rollno;
    }

    public void setRollno(String rollno) {

        this.rollno = rollno;
    }

    public String getDateofbirth() {

        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {

        this.dateofbirth = dateofbirth;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getYear() {

        return year;
    }

    public void setYear(String year) {

        this.year = year;
    }

    public String getSection() {

        return section;
    }

    public void setSection(String section) {

        this.section = section;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public String getUploaded_Date() {

        return uploaded_Date;
    }

    public void setUploaded_Date(String uploaded_Date) {

        this.uploaded_Date = uploaded_Date;
    }

}
