package prototype.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import prototype.domain.DateOperation;

@Service
public class DateOperationService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	List<DateOperation> results = new ArrayList<DateOperation>();
	
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
