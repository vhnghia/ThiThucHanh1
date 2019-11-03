package com.example.studentcontent;

public class Student {
    private int idStudent;
    private String tenSV;
    private String khoa;

    public Student(){
        super();
    }

    public Student(int idStudent, String tenSV, String khoa) {
        this.idStudent = idStudent;
        this.tenSV = tenSV;
        this.khoa = khoa;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", tenSV='" + tenSV + '\'' +
                ", khoa='" + khoa + '\'' +
                '}';
    }
}
