package prototype.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import prototype.domain.Account;

@Mapper
public interface AccountRepository {

	/*
	 *ユーザー検索
	 */
	@Select("SELECT * FROM account WHERE userName = #{userName}")
	Account findUser(String userName);
	
	/*
	 * ユーザー新規登録
	 */
	@Insert("INSERT INTO account(userName,password)  VALUES(#{userName},#{password})")
	void insert (Account account);
	
	/*
	 * ユーザー削除
	 */
	@Delete("DELETE FROM account WHERE userName = #{userName}")
	void deleteOne (String userName);
}
