package DxcApplication.LoginApp.models;

public class JWTResponse {
    private String JWT;

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String jWT) {
        JWT = jWT;
    }

    public JWTResponse(String jWT) {
        JWT = jWT;
    }

}
