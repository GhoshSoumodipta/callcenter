package at.ac.tuwien.sepm.groupphase.backend.domain.exception;

import java.io.Serial;

public class JwtTokenValidationException  extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 3624853630726946607L;

    public JwtTokenValidationException(String message){
        super(message);
    }

    public JwtTokenValidationException(String message, Throwable throwable){
        super(message, throwable);
    }
}
