package prototype.view.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class IndexPageTest {
	
	@BeforeClass
	public static void beforeClass() {
		
		Configuration.browser = WebDriverRunner.FIREFOX;
		
	    Configuration.holdBrowserOpen = true;
	    
	    /*
	     * テスト前にログイン
	     */
        open("http://localhost:8080/login");
        $("input[name=username]").val("user");
        $("input[name=password]").val("password");
        $("button[type=submit]").click();
	   
	}
	
	@Before
	public void setup() {
        open("http://localhost:8080/");
		
	}
	
    @Test
    public void TOP画面で参照をクリックして対象の計算式画面に移行すること() {
    	
        $(By.linkText("参照")).click();
        $("h1").shouldHave(text("計算式参照"));
        
    }
    
    @Test
    public void TOP画面で編集をクリックして対象の計算式画面に移行すること() {
    	
        $(By.linkText("編集")).click();
        $("h1").shouldHave(text("計算式編集"));
        
    }
    
    @Test
    public void TOP画面で基準日設定をクリックして計算基準設定画面に移行すること() {
    	
        $(By.linkText("基準日設定")).click();
        $("h1").shouldHave(text("日付計算基準設定"));
        
    }
    
    @Test
    public void TOP画面で新規登録をクリックして新規登録画面に移行すること() {
    	
        $(By.linkText("新規登録")).click();
        $("h1").shouldHave(text("計算式登録"));
        
    }
    
    @Test
    public void TOP画面でログアウトをクリックしてログアウトしてログイン画面に移行すること() {
    	
        $("input[value=ログアウト]").click();
        $("h1").shouldHave(text("日付計算"));
        
        $("input[name=username]").val("user");
        $("input[name=password]").val("password");
        $("button[type=submit]").click();
        
        
    }



}