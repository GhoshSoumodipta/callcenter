package at.ac.tuwien.sepm.groupphase.backend.adapter.config;

import at.ac.tuwien.sepm.groupphase.backend.domain.exception.JwtTokenValidationException;
import at.ac.tuwien.sepm.groupphase.backend.domain.exception.TooManyMsgException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler  extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties webProperties, ApplicationContext applicationContext, ServerCodecConfigurer configurer){
        super(errorAttributes, webProperties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
        super.setMessageReaders(configurer.getReaders());
    }
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request){
        Throwable exception = getError(request);
        if(exception instanceof JwtTokenValidationException){
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        }else if (exception instanceof TooManyMsgException){
            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ServerResponse.status(HttpStatus.NOT_FOUND).build();
    }
}
