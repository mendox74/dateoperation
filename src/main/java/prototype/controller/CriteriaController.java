package prototype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;
import prototype.service.DateOperationService;

@Controller
@RequestMapping("/")
public class CriteriaController {

    @Autowired
    private DateOperationRepository dateOperationRepository;
    public DateOperation daOpe = new DateOperation();
    
    /*
     * 基準日設定画面の表示
     */
    @GetMapping("criteria")
    public String criteria(Model model) {
        List<DateOperation> dateOperations = dateOperationRepository.findAll();
        model.addAttribute("dateOperations", dateOperations);
        model.addAttribute("daOpe", daOpe);
        return "criteria";
    }

    /*
     * 日付計算の結果
     */
    @PostMapping("criteria")
    public String result(@ModelAttribute DateOperation daOpe) {
		List<DateOperation> dateOperations = dateOperationRepository.findAll();
    	DateOperationService dateOperationService = new DateOperationService();
    	List<DateOperation> results = dateOperationService.calculate(daOpe.getCriteria(), dateOperations);
    	for(DateOperation result : results) {
    	dateOperationRepository.calulated(result);	
    	}
        return "redirect:/";
    }

}
