/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.scientist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author dbundgaard
 */
@RequestMapping("/")
@Controller
public class ScientistController {
    
    private final ScientistRepository repository;
    
    @Autowired
    public ScientistController(ScientistRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping
    public String index(@ModelAttribute("deleteMsg") String deleteMsg, Model model){
        Application.log.info("Delete Message: " + deleteMsg);
        
        model.addAttribute("deleteMsg", deleteMsg);
        model.addAttribute("scientists", repository.findAll());
        return "scientist/index";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveScientist(@ModelAttribute("scientist") Scientist scientist, final RedirectAttributes redirectAttributes) {
        if(repository.save(scientist) != null) {
            redirectAttributes.addFlashAttribute("saveScientist", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveScientist","failed");
        }
        return "redirect:/";
    } 
    @RequestMapping("/create")
    public String newScientist(Model model){
        return "scientist/create";
    }
    @RequestMapping("/deleteAll")
    public String deleteAll(final RedirectAttributes redirectAttributes){
        
        repository.deleteAll();
        if(repository.findAll().isEmpty()) {
            redirectAttributes.addFlashAttribute("deletedMsg", "No scientists found");
        } 
        return "redirect:/";
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteSpecific(@PathVariable("id") long id, final RedirectAttributes redirectAttributes){
        repository.delete(id);
        return "redirect:/";
    }
    
    
    @RequestMapping("/edit/{id}")
    public String updateSpecific(@PathVariable("id") long id, Model model){
        Scientist s = repository.findOne(id);
        if(s!=null){
            model.addAttribute("scientist", s);
            return "scientist/edit";
        } else {
            return "redirect:/";
        }
    }
}
