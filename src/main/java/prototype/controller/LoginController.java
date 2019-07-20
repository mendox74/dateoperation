package prototype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import prototype.domain.Account;
import prototype.repository.AccountRepository;

@Controller
@RequestMapping("/")
public class LoginController {
	
    @Autowired
    private AccountRepository accountRepository;
	
    /*
     * ログイン画面の表示
     */
    @GetMapping("login")
    public String login() {
        return "login";
    }
    
    /*
     * ユーザー新規登録画面の表示
     */
    @GetMapping("newUser")
    public String newUser(Model model) {
    	Account account = new Account();
    	model.addAttribute("account", account);
        return "newUser";
    }
    
    /*
     * ユーザー新規登録の挿入
     */
    @PostMapping("login")
    public String createUser(@Validated @ModelAttribute Account account, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) return "newUser";
    	accountRepository.insert(account);
        return "login";
    }
    
    /*
     * ユーザー登録の削除
     */
    @DeleteMapping("{userName}/delete")
    public String destroy(@PathVariable String userName,Model model) {
    	accountRepository.deleteOne(userName);
        model.addAttribute("userDelete", "アカウント削除しました！");
        return "login";
    }
}
