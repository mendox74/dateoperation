package prototype.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import prototype.repository.DateOperationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class EditControllerTest {

	private MockMvc mMvc;

	@MockBean
	private DateOperationRepository repository;

	@Autowired
	private EditController target;

	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	@Test
	public void 更新ページのリクエスト結果としてeditが返ること() throws Exception {
		mMvc.perform(get("/{id}/edit","1"))
			.andExpect(status().isOk())
			.andExpect(view().name("edit"));
		
		verify(repository,times(1)).findOne(1);
	}
	
	@Test
	public void 更新ページの情報を入力して実行するとredirectが返ること() throws Exception {
		mMvc.perform(put("/{id}","1")
				.param("operationId","Y01")
				.param("operationName","翌年")
				.param("operationYear","0")
				.param("operationMonth","0")
				.param("operationDay","0")
				.param("monthEnd","1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
		
	}
	
	@Test
	public void 更新ページの計算式IDに空白を入力して実行すると例外を持った状態でeditが返ること() throws Exception {
		mMvc.perform(put("/{id}","1")
				.param("operationId","")
				.param("operationName","翌年")
				.param("operationYear","0")
				.param("operationMonth","0")
				.param("operationDay","0")
				.param("monthEnd","1"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(view().name("edit"));
		
	}
	
	@Test
	public void 更新ページの計算式名に空白を入力して実行すると例外を持った状態でeditが返ること() throws Exception {
		mMvc.perform(put("/{id}","1")
				.param("operationId","Y01")
				.param("operationName","")
				.param("operationYear","0")
				.param("operationMonth","0")
				.param("operationDay","0")
				.param("monthEnd","1"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(view().name("edit"));
		
	}
	
	@Test
	public void 更新ページの計算式年に空白を入力して実行すると例外を持った状態でeditが返ること() throws Exception {
		mMvc.perform(put("/{id}","1")
				.param("operationId","Y01")
				.param("operationName","翌年")
				.param("operationYear","")
				.param("operationMonth","0")
				.param("operationDay","0")
				.param("monthEnd","1"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(view().name("edit"));
		
	}
	
	@Test
	public void 更新ページの計算式月に空白を入力して実行すると例外を持った状態でeditが返ること() throws Exception {
		mMvc.perform(put("/{id}","1")
				.param("operationId","Y01")
				.param("operationName","翌年")
				.param("operationYear","0")
				.param("operationMonth","")
				.param("operationDay","0")
				.param("monthEnd","1"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(view().name("edit"));
		
	}
	
	@Test
	public void 更新ページの計算式日に空白を入力して実行すると例外を持った状態でeditが返ること() throws Exception {
		mMvc.perform(put("/{id}","1")
				.param("operationId","Y01")
				.param("operationName","翌年")
				.param("operationYear","0")
				.param("operationMonth","0")
				.param("operationDay","")
				.param("monthEnd","1"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(view().name("edit"));
		
	}

}
