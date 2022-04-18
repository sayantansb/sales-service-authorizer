package api.sales.service.authorizers.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    @Value("${sales.service.app}")
    private String app;

    @Value("${sales.service.env}")
    private String env;

    @Value("${sales.service.token}")
    private String token;

    public Boolean getSub(String app,String env,String token)  {
        if(app==null || env==null || token==null){
            return false;
        }
        return app.equals(this.app) && env.equals(this.env) && token.equals(this.token);
    }
}
