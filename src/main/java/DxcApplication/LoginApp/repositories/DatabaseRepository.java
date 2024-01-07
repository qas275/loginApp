package DxcApplication.LoginApp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import DxcApplication.LoginApp.models.User;

import static DxcApplication.LoginApp.repositories.Queries.*;

import java.util.Optional;

@Repository
public class DatabaseRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean checkLoginSQL(String email, String password){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(LOGIN_CHECK_Q, email, password);
        boolean login = false;
        while(rs.next()){
            login = rs.getInt("exist")>0;
        }
        return login;
    }

    public Optional<User> loadUser(String username){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(LOGIN_Q, username);
        rs.next();
        Optional<User> user  = User.createUser(rs);
        return user;
    }
}
