package at.ac.tuwien.sepm.groupphase.backend.domain.exception;

import java.io.Serial;

public class TooManyMsgException  extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -1238460765678274133L;

    public TooManyMsgException(String message){
        super(message);
    }
}
