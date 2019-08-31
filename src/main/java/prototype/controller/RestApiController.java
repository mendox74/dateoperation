package prototype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prototype.domain.DateOperation;
import prototype.service.DateOperationService;

@RestController
@RequestMapping("/api")
public class RestApiController {
	
	@Autowired
	private DateOperationService service;
	
	/*
	 * 計算式を全件検索して一覧取得
	 */
	@GetMapping
	public List<DateOperation>  GetDateOperationList(){
		return service.findAll();
	}
	
	/*
	 * 計算式を一件検索して取得
	 */
	@GetMapping(value = "{id}")
	public DateOperation findOne(@PathVariable("id") int id){
		return service.findOne(id);
	}
	

}
