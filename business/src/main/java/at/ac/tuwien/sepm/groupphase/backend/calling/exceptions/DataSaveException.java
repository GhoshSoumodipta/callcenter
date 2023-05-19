package at.ac.tuwien.sepm.groupphase.backend.calling.exceptions;

import java.io.Serial;

public class DataSaveException  extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 333910422380714481L;

    public DataSaveException(String message) {
        super(message);
    }
}
