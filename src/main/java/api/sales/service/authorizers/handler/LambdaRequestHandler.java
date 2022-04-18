package api.sales.service.authorizers.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class LambdaRequestHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, Object> {
}
