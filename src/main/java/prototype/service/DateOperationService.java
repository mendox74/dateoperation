package prototype.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prototype.domain.DateOperation;
import prototype.repository.DateOperationRepository;

@Service
public class DateOperationService {
	
	@Autowired
	private DateOperationRepository dateOperationRepository;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	List<DateOperation> results = new ArrayList<DateOperation>();
	
	/*
	 * 計算式を全件検索して一覧取得
	 */
	public List<DateOperation> findAll(){
		return dateOperationRepository.findAll();
	}
	
	/*
	 * 計算式を一件検索して取得
	 */
	public DateOperation findOne(int id){
		return dateOperationRepository.findOne(id);
	}
	
	/*
	 * 計算式を新規登録
	 */
	@Transactional
	public void insert(DateOperation dateOperation){
		dateOperationRepository.insert(dateOperation);
	}
	
	/*
	 * 計算式を更新
	 */
	@Transactional
	public void updata(DateOperation dateOperation){
		dateOperationRepository.updata(dateOperation);
	}
	
	/*
	 * 計算式を削除
	 */
	@Transactional
	public void delete(int id){
		dateOperationRepository.deleteOne(id);
	}
	
	/*
	 * 日付演算ロジック
	 */
	@Transactional
	public List<DateOperation> calculate(String criteria ,List<DateOperation> dateOperations) throws ParseException {
		
		Calendar cal = Calendar.getInstance();

		for(DateOperation daOpe : dateOperations) {
			try{
				cal.setTime(sdf.parse(criteria));
			}catch(ParseException ex){
				throw ex;
	        }
			cal.add(Calendar.YEAR,daOpe.getOperationYear());
			cal.add(Calendar.MONTH,daOpe.getOperationMonth());
			if (daOpe.getMonthEnd() == 1) {
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
			cal.add(Calendar.DAY_OF_MONTH,daOpe.getOperationDay());	
			
			daOpe.setResult(sdf.format(cal.getTime()));
			results.add(daOpe);
		}
		return results;
	}
}
