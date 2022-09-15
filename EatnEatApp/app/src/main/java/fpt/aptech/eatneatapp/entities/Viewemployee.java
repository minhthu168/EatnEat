package fpt.aptech.eatneatapp.entities;

import java.io.Serializable;

public class Viewemployee implements Serializable {
    private String empid;
    private String empname;
    private String phone;

    public Viewemployee() {
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Viewemployee{" +
                "empid='" + empid + '\'' +
                ", empname='" + empname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
