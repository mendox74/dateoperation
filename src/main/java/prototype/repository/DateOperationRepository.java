package prototype.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import prototype.domain.DateOperation;

@Mapper
public interface DateOperationRepository {
	
	/*
	 * 全件取得
	 */
	@Select("SELECT * FROM date ORDER BY id ASC")
	List<DateOperation> findAll();
	
	/*
	 *個別取得
	 */
	@Select("SELECT * FROM date WHERE id = #{id}")
	DateOperation findOne(int id);
	
	/*
	 * 新規登録
	 */
	@Insert("INSERT INTO date(operationId,operationName,operationYear,operationMonth,operationDay) VALUES(#{operationId},#{operationName},#{operationYear},#{operationMonth},#{operationDay})")
	void insert (DateOperation dateOperation);
	
	/*
	 * 計算式更新
	 */
	@Update("UPDATE date SET operationId = #{operationId}, operationName = #{operationName}, operationYear = #{operationYear}, operationMonth = #{operationMonth}, operationDay = #{operationDay} WHERE id = #{id}")
	void updata (DateOperation dateOperation);
	
	/*
	 * 結果更新
	 */
	@Update("UPDATE date SET result = #{result} WHERE id = #{id}")
	void calulated (DateOperation dateOperation);
	
	/*
	 * 削除
	 */
	@Delete("DELETE FROM date WHERE id = #{id}")
	void deleteOne (int id);
}
