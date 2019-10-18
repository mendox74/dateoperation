package prototype.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import prototype.domain.Account;

@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository art;
	
	@Test
	public void ユーザー名から検索して1件取得できること() throws Exception {
		Account test = art.findUser("おおや");
		
		assertThat(test.getId()).isEqualTo(1);
		assertThat(test.getUserName()).isEqualTo("おおや");
		assertThat(test.getPassword()).isEqualTo("aaaaa");
		assertThat(test.getAuthority()).isEqualTo("USER");
	}

	@Test
	public void 存在しないユーザーが検索されたときNullが返ってくること() throws Exception {
		Account test = art.findUser("やまだ");
		
		assertThat(test).isNull();
	}

	@Test
	public void ユーザー新規登録ができること() throws Exception {
		Account testData = setAccount("こにし","desukedo");
		
		art.insert(testData);
		Account test = art.findUser("こにし");
		assertThat(test.getPassword()).isEqualTo("desukedo");
	}
	
	@Test
	public void ユーザー名に11文字以上を登録しようとすると例外がかえってくること() throws Exception {
		Account testData = setAccount("12345678910","desukedo");

		assertThatThrownBy(() -> {
			art.insert(testData);
		}).isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	public void パスワードに11文字以上を登録しようとすると例外がかえってくること() throws Exception {
		Account testData = setAccount("こにし","12345678910");

		assertThatThrownBy(() -> {
			art.insert(testData);
		}).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	public void ユーザー登録を削除できること() throws Exception {
		art.deleteOne("おおや");
		Account test = art.findUser("おおや");
		
		assertThat(test).isNull();
	}
	
	private Account setAccount (String userName,String password) {
		Account account = new Account();
		account.setUserName(userName);
		account.setPassword(password);
		return account;
	}

}
