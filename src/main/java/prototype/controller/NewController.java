package prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;

@Controller
@RequestMapping("/")
public class NewController {

    @Autowired
    private DateOperationRepository dateOperationRepository;
    public DateOperation daOpe = new DateOperation();

    /*
     * 新規登録画面の表示
     */
    @GetMapping("new")
    public String newDateOperation(Model model) {
    	DateOperation dateOperation = new DateOperation();
    	model.addAttribute("dateOperation", dateOperation);
        return "new";
    }
 
    /*
     * 新規登録の挿入
     */
    @PostMapping
    public String create(@Validated @ModelAttribute DateOperation dateOperation, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) return "new";
    	dateOperationRepository.insert(dateOperation);
        return "redirect:/";
    }

}
