package prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;

@Controller
@RequestMapping("/")
public class ShowController {

    @Autowired
    private DateOperationRepository dateOperationRepository;
    public DateOperation daOpe = new DateOperation();

    /*
     * 参照画面の表示
     */
    @GetMapping("{id}")
    public String show(@PathVariable int id, Model model) {
    	DateOperation dateOperation = dateOperationRepository.findOne(id);
        model.addAttribute("dateOperation", dateOperation);
        return "show";
    }
}
