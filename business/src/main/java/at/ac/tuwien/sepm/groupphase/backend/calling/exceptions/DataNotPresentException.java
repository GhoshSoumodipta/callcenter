package at.ac.tuwien.sepm.groupphase.backend.calling.exceptions;

import java.io.Serial;

public class DataNotPresentException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2729025311376486854L;

    public DataNotPresentException(String message) {
        super(message);
    }
}
