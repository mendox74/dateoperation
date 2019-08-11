package prototype.view.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class LoginPageTest {
	
	@BeforeClass
	public static void beforeClass() {
		
		Configuration.browser = WebDriverRunner.FIREFOX;
		
	    Configuration.holdBrowserOpen = true;
	   
	}
	
	@Before
	public void setup() {
        open("http://localhost:8080/login");
		
	}

    @Test
    public void ログイン画面で名前とパスワードが空欄でログイン失敗してエラーメッセージが表示されること() {
        
        $("button[type=submit]").click();
        $("div").shouldHave(text("ログインに失敗しました。再度入力をお願いします"));
    }
    
    @Test
    public void ログイン画面で名前が違うときログイン失敗してエラーメッセージが表示されること() {
    	
        $("input[name=username]").val("おおや");
        $("input[name=password]").val("password");
        $("button[type=submit]").click();
        $("div").shouldHave(text("ログインに失敗しました。再度入力をお願いします"));
    }
    
    @Test
    public void ログイン画面でパスワードが違うときログイン失敗してエラーメッセージが表示されること() {
    	
        $("input[name=username]").val("user");
        $("input[name=password]").val("パスワード");
        $("button[type=submit]").click();
        $("div").shouldHave(text("ログインに失敗しました。再度入力をお願いします"));
    }
      
    @Test
    public void ログイン画面で名前とパスワードを正しく入力してログインが成功できること() {
    	
        $("input[name=username]").val("user");
        $("input[name=password]").val("password");
        $("button[type=submit]").click();
        $("h1").shouldHave(text("日付計算式一覧"));
    }
    
    @Test
    public void ログイン画面から新規登録画面へ移行すること() {
    	
        $("a").click();
        $("h1").shouldHave(text("ユーザー新規登録"));
    }

}
