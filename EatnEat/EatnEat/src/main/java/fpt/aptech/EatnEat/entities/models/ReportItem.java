/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class ReportItem implements Serializable{
    private int value;
    private String time;

    public ReportItem() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    
}
