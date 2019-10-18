package prototype.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import prototype.repository.DateOperationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class IndexControllerTest {

	private MockMvc mMvc;

	@MockBean
	private DateOperationRepository repository;

	@Autowired
	private IndexController target;

	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	@Test
	@WithMockUser(username="おおや",password="password",roles="USER")
	public void TOPページのリクエスト結果としてindexが返ること() throws Exception {
		mMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
		
		verify(repository,times(1)).findAll();
	}
	
	@Test
	@WithMockUser(username="おおや",password="password",roles="USER")
	public void ログインを実行してindexが返ること() throws Exception {
		mMvc.perform(post("/sign_in"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
		
		verify(repository,times(1)).findAll();
	}
	
	@Test
	public void 計算式の削除を実行してindexが返ること() throws Exception {
		mMvc.perform(delete("/{id}","1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
		
		verify(repository,times(1)).deleteOne(1);;
	}


}
