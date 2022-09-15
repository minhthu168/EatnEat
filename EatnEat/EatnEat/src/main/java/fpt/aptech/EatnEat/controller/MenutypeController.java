/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.Menutype;
import fpt.aptech.EatnEat.service.MenutypeService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lenovo
 */
@Controller
public class MenutypeController {

    @Autowired
    public MenutypeService menutypeService;
    @Autowired
    HttpSession session;

    @GetMapping("admin/indexMenu")
    public String indexMenu(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("listMenu", menutypeService.findAllMenu());
            model.addAttribute("type", new Menutype());
            return "admin/indexMenu";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("admin/doCreateMenu")
    public String doCreateMenu(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("menu", new Menutype());
            return "admin/createMenu";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("admin/createMenu")
    public String createMenu(Model model, Menutype newMenu, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        if (session.getAttribute("ADMIN") != null) {
            String fileName = multipartFile.getOriginalFilename();
            newMenu.setTypeimg(fileName);

            Menutype savedMenu = menutypeService.saveMenutype(newMenu);

            model.addAttribute("list", menutypeService.findAllMenu());

            String uploadDir = "./images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file" + fileName);
            }

            return "redirect:../admin/indexMenu";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("admin/deleteMenu/{id}")
    public String deleteMenu(Model model, @PathVariable("id") Integer id) {
        if (session.getAttribute("ADMIN") != null) {
            Menutype menu = menutypeService.findOne(id);
            if (menu != null) {
                menutypeService.deleteMenu(menu);
            }
            return "redirect:../admin/indexMenu";
        } else {
            return "redirect:/login";
        }
    }

}
