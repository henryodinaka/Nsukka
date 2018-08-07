/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LEOGOLD
 */
@Controller
@RequestMapping(value = "/user")
public class CommentService {
    
       
     @RequestMapping(value = "/comment")
     public String commentPage(){
         return "comment";
     } 
}
