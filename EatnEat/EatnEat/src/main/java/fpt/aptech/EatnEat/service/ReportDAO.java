/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Orders;
import fpt.aptech.EatnEat.entities.models.ReportItem;
import fpt.aptech.EatnEat.repository.OrderRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDAO {

    @Autowired
    OrderRepository repository;

    public List<ReportItem> reportReceipt(Date date, int limit) {
        List<ReportItem> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDays(date, i);
            ReportItem myItem = new ReportItem();
            myItem.setTime(covertD2S(d));
            myItem.setValue(countItemByDate(d));
            list.add(myItem);
        }
        return list;
    }

    public List<ReportItem> reportReceiptMonth(Date date, int limit) {
        List<ReportItem> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            ReportItem myItem = new ReportItem();  
            String month=subMonth(date, i).toString();
            myItem.setTime(covertD1S(subMonth(date, i)));
            myItem.setValue(countItemByMonth(subMonth(date,i)));
            list.add(myItem);
        }
        return list;
    }

    private int countItemByMonth(Date date) {
        List<Orders> list = repository.OrdersOfMonthList(date);
        return list.size();
    }

    private int countItemByDate(Date date) {
        List<Orders> list = repository.OrdersOfDayList(date);
        return list.size();
    }
    public static Date addMonth(Date date, int x) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, x);
        return cal.getTime();
    }

    public static Date subMonth(Date date, int x) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -x);
        return cal.getTime();
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date subDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM");
        return df.format(date);
    }
    private String covertD1S(Date date) {
        DateFormat df = new SimpleDateFormat("MM");
        return df.format(date);
    }

}
