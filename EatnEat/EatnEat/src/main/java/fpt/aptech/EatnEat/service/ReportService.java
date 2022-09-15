/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.models.ReportItem;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class ReportService {
    @Autowired
    private ReportDAO reportDAO;

   
    public List<ReportItem> reportReceipt(Date date, int limit) {
        return reportDAO.reportReceipt(date, limit);
    }
     public List<ReportItem> reportReceiptMonth(Date date, int limit) {
        return reportDAO.reportReceiptMonth(date, limit);
    }
    

}
