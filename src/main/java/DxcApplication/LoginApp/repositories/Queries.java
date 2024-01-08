package DxcApplication.LoginApp.repositories;

public class Queries {
    public static final String LOGIN_CHECK_Q = "select (count(username)>0) as exist from users where BINARY username = ? and BINARY password = ?";
    public static final String LOGIN_Q = "select * from users where BINARY username = ?";
}
