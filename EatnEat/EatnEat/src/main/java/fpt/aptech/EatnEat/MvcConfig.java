/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author lenovo
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //exposeDirectory("uploads", registry);
        Path foodUploadDir = Paths.get("./images");
        String foodUploadPath = foodUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/images/**").addResourceLocations("file:/" + foodUploadPath + "/");
    }
    
//    public void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
//        Path uploadDir = Paths.get(dirName);
//        String uploadPath = uploadDir.toFile().getAbsolutePath();
//        
//        if(dirName.startsWith("../")) {
//            dirName = dirName.replace("../", "");
//        }
//        
//        registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:///"+uploadPath+"/");
//    }
}
