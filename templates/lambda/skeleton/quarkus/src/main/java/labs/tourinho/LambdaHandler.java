package labs.tourinho;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import jakarta.inject.Named;

/** Lambda ${{ values.name }} — ${{ values.description }} */
@Named("handler")
public class LambdaHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        return "hello from ${{ values.name }}";
    }
}
