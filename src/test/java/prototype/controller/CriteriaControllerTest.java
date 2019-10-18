package prototype.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import prototype.repository.DateOperationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CriteriaControllerTest {

	private MockMvc mMvc;

	@MockBean
	private DateOperationRepository repository;

	@Autowired
	private CriteriaController target;

	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	@Test
	public void 基準日設定ページのリクエスト結果としてcriteriaが返ること() throws Exception {
		mMvc.perform(get("/criteria"))
			.andExpect(status().isOk())
			.andExpect(view().name("criteria"));
		
		verify(repository,times(1)).findAll();
	}
	
	@Test
	public void 基準日設定ページで計算実行するとredirectが返ること() throws Exception {
		mMvc.perform(post("/criteria"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
		
		verify(repository,times(1)).findAll();
	}
	
}
