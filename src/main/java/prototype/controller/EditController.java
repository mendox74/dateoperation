package prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;

@Controller
@RequestMapping("/")
public class EditController {

    @Autowired
    private DateOperationRepository dateOperationRepository;
    public DateOperation daOpe = new DateOperation();

    /*
     * 編集画面の表示
     */
    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) {
    	DateOperation dateOperation = dateOperationRepository.findOne(id);
        model.addAttribute("dateOperation", dateOperation);
        return "edit";
    }

    /*
     * 編集情報の更新
     */
    @PutMapping("{id}")
    public String update(@PathVariable int id, @Validated @ModelAttribute DateOperation dateOperation, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) return "edit";
    	dateOperation.setId(id);
    	dateOperationRepository.updata(dateOperation);
        return "redirect:/";
    }
}
