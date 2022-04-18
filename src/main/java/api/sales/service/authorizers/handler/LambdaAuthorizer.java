package api.sales.service.authorizers.handler;

import api.sales.service.authorizers.util.AuthUtil;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import api.sales.service.authorizers.response.PolicyDocument;
import api.sales.service.authorizers.response.Response;
import api.sales.service.authorizers.response.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class LambdaAuthorizer {

    @Autowired
    private AuthUtil authUtil;


    public Response handleRequestEvent(APIGatewayProxyRequestEvent event) {
        Map<String, String> headers = event.getHeaders();
        String app = headers.get("X-SEM-APP");
        String env = headers.get("X-SEM-ENV");
        String token = headers.get("X-SEM-TOKEN");


        String auth = "Deny";
        boolean valid = authUtil.getSub(app,env,token);
        if (valid) {
            auth = "Allow";
        }

        Map<String, String> ctx = new HashMap<String, String>();
        ctx.put("sub", "sales-service-client");

        APIGatewayProxyRequestEvent.ProxyRequestContext proxyContext = event.getRequestContext();
        APIGatewayProxyRequestEvent.RequestIdentity identity = proxyContext.getIdentity();


        String arn = String.format("arn:aws:execute-api:%s:%s:%s/%s/%s/%s",System.getenv("AWS_REGION"), proxyContext.getAccountId(),
                proxyContext.getApiId(), proxyContext.getStage(), proxyContext.getHttpMethod(), "*");

        Statement statement = Statement.builder().effect(auth).resource(arn).build();

        PolicyDocument policyDocument = PolicyDocument.builder().statements(Collections.singletonList(statement))
                .build();

        return Response.builder().principalId(identity.getAccountId()).policyDocument(policyDocument)
                .context(ctx).build();
    }
}

