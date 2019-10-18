package prototype.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import prototype.domain.DateOperation;

@RunWith(Enclosed.class)
public class DateOperationServiceTest {
	
	public static class 日付計算例外処理テスト{
		
		private DateOperationService dost;
		private List<DateOperation> test = new ArrayList<DateOperation>();
		
		@Before
		public void setup () throws Exception {
			dost = new DateOperationService();
		}
		
		@Test
		public void 基準日にyyyymmdd形式以外で渡すとParseExceptionがかえってくること() throws Exception {
			test.add(setDateOperation(0,0,0));
			assertThatThrownBy(() -> {
				dost.calculate("2019", test);
			}).isInstanceOf(ParseException.class);	
		}
		
		@Test
		public void 基準日にNullを渡すとNullPointerExceptionがかえってくること() throws Exception {
			test.add(setDateOperation(0,0,0));
			assertThatThrownBy(() -> {
				dost.calculate(null, test);
			}).isInstanceOf(NullPointerException.class);	
		}
		
		@Test
		public void 計算式にNullを渡すとNullPointerExceptionがかえってくること() throws Exception {
			assertThatThrownBy(() -> {
				dost.calculate("20190707", null);
			}).isInstanceOf(NullPointerException.class);	
		}
		
		private DateOperation setDateOperation (int Year,int Month,int Day) {
			DateOperation Do = new DateOperation();
			Do.setOperationYear(Year);
			Do.setOperationMonth(Month);
			Do.setOperationDay(Day);			
			return Do;
		}
	}

	@RunWith(Parameterized.class)
	public static class 日付計算パターンテスト{
		
		private String 基準日;
		private int 年;
		private int 月;
		private int 日;
		private String 結果;
		
		@Parameters(name = "{index} 基準日:{0}, 年:{1}, 月:{2}, 日:{3}, 結果:{4}")
		public static Object[][] params() {
			return new Object[][] {
				/* 
				 * すべて未指定
				 */
				{"20191201", 0, 0, 0, "20191201"},
				/*  
				 * すべて指定
				 */
				{"20191201", 1, 1, 1, "20210102"},
				/*
				 *  月の加算で日数の切り捨て
				 */
				{"20191031", 0, 1, 0, "20191130"},
				/*
				 *  年月またぎ
				 */
				{"20191202", 0, 13, 0, "20210102"},
				{"20181202", 0, 0, 365, "20191202"},
				/*
				 * 閏年366日
				 */
				{"20200101", 0, 0, 366, "20210101"},
				/*
				 *  翌日
				 */
				{"20191201", 0, 0, 1, "20191202"},
				/*
				 *  前日
				 */
				{"20191201", 0, 0, -1, "20191130"},
				/*
				 *  翌月
				 */
				{"20191101", 0, 1, 0, "20191201"},
				/*
				 *  前月
				 */
				{"20191201", 0, -1, 0, "20191101"},
				/*
				 *  翌年
				 */
				{"20191201", 1, 0, 0, "20201201"},
				/*
				 *  前年
				 */
				{"20191201", -1, 0, 0, "20181201"},
			};
		}
		
		public 日付計算パターンテスト(String 基準日, int 年, int 月, int 日, String 結果) {
			this.基準日 = 基準日;
			this.年 = 年;
			this.月 = 月;
			this.日 = 日;
			this.結果 = 結果;
		}
		
		@Test
		public void testPattern() throws Exception {
			DateOperation pattern = new DateOperation();
			List<DateOperation> test = new ArrayList<DateOperation>();
			pattern.setOperationYear(年);
			pattern.setOperationMonth(月);
			pattern.setOperationDay(日);
			test.add(pattern);

			DateOperationService sut = new DateOperationService();
			List<DateOperation> testResult = sut.calculate(基準日,test);

			assertThat(testResult.get(0).getResult()).isEqualTo(結果);
		}
		
	}
	
}
