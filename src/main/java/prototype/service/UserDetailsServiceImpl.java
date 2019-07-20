package prototype.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import prototype.repository.AccountRepository;
import prototype.domain.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    //DBからユーザ情報を検索する
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Account account = accountRepository.findUser(userName);

        if (account == null) {
            throw new UsernameNotFoundException("Account" + userName + "was not found in the database");
        }
        //権限のリスト
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("account.authority");
        grantList.add(authority);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserDetails userDetails = (UserDetails)new User(account.getUserName(), encoder.encode(account.getPassword()),grantList);

        return userDetails;
    }
}