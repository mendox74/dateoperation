package prototype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private DateOperationRepository dateOperationRepository;
    public DateOperation daOpe = new DateOperation();
    
   /*
    * 一覧画面の表示
    */
    @GetMapping
    public String indexDateOperations(Model model) {
      /*
       * ログインユーザの情報を取得
       */
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userName = auth.getName();
      model.addAttribute("userName", userName);
      
      List<DateOperation> dateOperations = dateOperationRepository.findAll();
      model.addAttribute("dateOperations", dateOperations);
      return "index";
    }
    
    /*
     * ログイン成功時の画面表示
     */
    @PostMapping("sign_in")
    public String sign_in(Model model) {
       /*
        * ログインユーザの情報を取得
        */
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String userName = auth.getName();
       model.addAttribute("userName", userName);
       List<DateOperation> dateOperations = dateOperationRepository.findAll();
       model.addAttribute("dateOperations", dateOperations);
       model.addAttribute("login", "ログインしました！");
       return "index";
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
