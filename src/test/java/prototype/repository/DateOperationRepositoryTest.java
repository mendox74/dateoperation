package prototype.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import prototype.domain.DateOperation;

@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class DateOperationRepositoryTest {

	@Autowired
	private DateOperationRepository dort;
	
	@Test
	public void 全件取得ができること() throws Exception {
		List<DateOperation> test = dort.findAll();
		
		assertThat(test.size()).isEqualTo(2);
	}

	@Test
	public void 個別検索ができること() throws Exception {
		DateOperation test = dort.findOne(1);
		
		assertThat(test.getOperationId()).isEqualTo("Y01");
		assertThat(test.getOperationName()).isEqualTo("翌年");
		assertThat(test.getOperationYear()).isEqualTo(1);
		assertThat(test.getOperationMonth()).isEqualTo(0);
		assertThat(test.getOperationDay()).isEqualTo(0);
	}

	@Test
	public void 存在しない個別検索がされたときNullが返されること() throws Exception {
		DateOperation test = dort.findOne(10);
		
		assertThat(test).isNull();
	}
	
	@Test
	public void 登録_新規登録ができること() throws Exception {
		DateOperation testData = setData(3,"M02","2ヵ月後",0,2,0,"00000000",0);
		
		dort.insert(testData);
		DateOperation test = dort.findOne(3);
		
		assertThat(test.getOperationId()).isEqualTo("M02");
	}

	@Test
	public void 検索した1件について更新ができること() throws Exception {
		DateOperation testData = setData(2,"D01","翌日",0,0,1,"00000000",0);
		
		dort.updata(testData);
		DateOperation test = dort.findOne(2);
		
		assertThat(test.getOperationId()).isEqualTo("D01");
		assertThat(test.getOperationName()).isEqualTo("翌日");
		assertThat(test.getOperationYear()).isEqualTo(0);
		assertThat(test.getOperationMonth()).isEqualTo(0);
		assertThat(test.getOperationDay()).isEqualTo(1);
	}
	
	@Test
	public void 検索した1件について計算結果が更新できること() throws Exception {
		DateOperation testData = setData(2,"M01","翌月",0,1,0,"55555555",0);
		
		dort.calulated(testData);
		DateOperation test = dort.findOne(2);
		
		assertThat(test.getResult()).isEqualTo("55555555");
		
	}
	
	@Test
	public void 検索した1件について削除ができること() throws Exception {
		dort.deleteOne(2);
		
		List<DateOperation> test = dort.findAll();
		
		assertThat(test.size()).isEqualTo(1);
	}
	
	@Test
	public void 登録_計算式IDに11文字以上の文字列を登録しようとすると例外がかえってくること() throws Exception {
		DateOperation testData = setData(0, "1234567891011","2ヵ月後",0,2,0,"00000000",0);

		assertThatThrownBy(() -> {
			dort.insert(testData);
		}).isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	public void 登録_計算式名に11文字以上の文字列を登録しようとすると例外がかえってくること() throws Exception {
		DateOperation testData = setData(0, "M02","1234567891011",0,2,0,"00000000",0);

		assertThatThrownBy(() -> {
			dort.insert(testData);
		}).isInstanceOf(DataIntegrityViolationException.class);
	}
	
	
	private DateOperation setData(int id,String OpeId,String Name,int Year, int Month, int Day , String result,int MonthEnd) {
		DateOperation setData = new DateOperation();
		setData.setId(id);
		setData.setOperationId(OpeId);
		setData.setOperationName(Name);
		setData.setOperationYear(Year);
		setData.setOperationMonth(Month);
		setData.setOperationDay(Day);
		setData.setResult(result);
		setData.setMonthEnd(MonthEnd);
		return setData;
	}

}
