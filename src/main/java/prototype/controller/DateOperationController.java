package prototype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;
import prototype.service.DateOperationService;

@Controller
@RequestMapping("/")
public class DateOperationController {

    @Autowired
    private DateOperationRepository dateOperationRepository;
    public DateOperation daOpe = new DateOperation();
   /*
    * 一覧画面の表示
    */
    @GetMapping
    public String indexDateOperations(Model model) {
      List<DateOperation> dateOperations = dateOperationRepository.findAll();
      model.addAttribute("dateOperations", dateOperations);
      return "index";
    }
    
    /*
     * 計算結果画面の表示
     */
     @GetMapping("result")
     public String resultDateOperations(Model model) {
       List<DateOperation> dateOperations = dateOperationRepository.findAll();
       model.addAttribute("dateOperations", dateOperations);
       return "result";
     }
    
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
     * 編集画面の表示
     */
    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) {
    	DateOperation dateOperation = dateOperationRepository.findOne(id);
        model.addAttribute("dateOperation", dateOperation);
        return "edit";
    }
    
    /*
     * 参照画面の表示
     */
    @GetMapping("{id}")
    public String show(@PathVariable int id, Model model) {
    	DateOperation dateOperation = dateOperationRepository.findOne(id);
        model.addAttribute("dateOperation", dateOperation);
        return "show";
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
    
    /*
     * 登録情報の削除
     */
    @DeleteMapping("{id}")
    public String destroy(@PathVariable int id) {
    	dateOperationRepository.deleteOne(id);
        return "redirect:/";
    }

}
