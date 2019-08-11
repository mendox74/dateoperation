package prototype.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import prototype.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class LoginControllerTest {

	private MockMvc mMvc;

	@MockBean
    private AccountRepository accountRepository;

	@Autowired
	private LoginController target;

	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	@Test
	public void ログインページのリクエスト結果としてloginが返ること() throws Exception {
		mMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
		
	}
	
	@Test
	public void ユーザー新規登録ページのリクエスト結果としてnewUserが返ること() throws Exception {
		mMvc.perform(get("/newUser"))
			.andExpect(status().isOk())
			.andExpect(view().name("newUser"));
		
	}
	
	@Test
	public void ログインページでユーザーを入力してloginが返ること() throws Exception {
		mMvc.perform(post("/login").param("userName", "おおや").param("password", "password"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
		
	}
	
	@Test
	public void ログインページでユーザーをNullして例外情報が入った状態でnewUserが返ること() throws Exception {
		mMvc.perform(post("/login"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(view().name("newUser"));
		
	}
	
	@Test
	public void ログインページで削除を行うと処理が行われてloginが返ること() throws Exception {
		mMvc.perform(delete("/{userName}/delete","user"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
		
		verify(accountRepository, times(1)).deleteOne("user");
	}


}
