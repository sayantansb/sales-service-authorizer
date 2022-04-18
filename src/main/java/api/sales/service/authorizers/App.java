package api.sales.service.authorizers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import api.sales.service.authorizers.handler.LambdaAuthorizer;
import api.sales.service.authorizers.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private LambdaAuthorizer lambdaAuthorizer;

    @Bean
    public Function<APIGatewayProxyRequestEvent, Response> validation(){
        return (event) -> lambdaAuthorizer.handleRequestEvent(event);
    }
}
