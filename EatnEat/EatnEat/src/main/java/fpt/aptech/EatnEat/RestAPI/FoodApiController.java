/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.RestAPI;

import fpt.aptech.EatnEat.entities.Menutype;
import fpt.aptech.EatnEat.entities.Viewfood;
import fpt.aptech.EatnEat.repository.MenutypeRepository;
import fpt.aptech.EatnEat.repository.ViewFoodRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@RequestMapping("/api")
public class FoodApiController {
    @Autowired
    ViewFoodRepository service;
    @Autowired
    MenutypeRepository menutypeRepository;
    
    public FoodApiController(ViewFoodRepository service){
        this.service=service;
    }
  
    @GetMapping("/menuSet")
    public ResponseEntity<List<Viewfood>> setFood() {
        List<Viewfood> list=service.setList();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }     
    }
    @GetMapping("/menuSet/{menuid}")
    public ResponseEntity<List<Viewfood>> searchByCat_SetList(@PathVariable("menuid")Integer menuid) {
        List<Viewfood> list=service.searchByCat_SetList(menuid);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }     
    }
    @GetMapping("/menuOption")
    public ResponseEntity<List<Viewfood>> optionFood() {
        List<Viewfood> list=service.optionList();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }     
    }
    @GetMapping("/menuOption/{menuid}")
    public ResponseEntity<List<Viewfood>> searchByCat_OptionList(@PathVariable("menuid")Integer menuid) {
        List<Viewfood> list=service.searchByCat_OptionList(menuid);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }     
    }
    @GetMapping("/menutype")
    public ResponseEntity<List<Menutype>> MenuType() {
        List<Menutype> list=menutypeRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }     
    }
    
}
