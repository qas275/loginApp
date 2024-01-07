package DxcApplication.LoginApp.models;

public class JWTRequest {
    
    private String jwt;
    

    public JWTRequest(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
