/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Menutype;
import fpt.aptech.EatnEat.repository.MenutypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author THINKPAD
 */
@Service
public class MenutypeService {
    @Autowired
    public MenutypeRepository menutypeRepository;
    
    public List<Menutype> findAllMenu() {
        return menutypeRepository.findAll();
    }
    
    public Menutype findOne(Integer menuid) {
        return menutypeRepository.findByMenutypeid(menuid);
    }
    
    public Menutype saveMenutype(Menutype newMenu) {
       return menutypeRepository.save(newMenu);
    }
    
    public void deleteMenu(Menutype delMenutype) {
        menutypeRepository.delete(delMenutype);
    }
}
