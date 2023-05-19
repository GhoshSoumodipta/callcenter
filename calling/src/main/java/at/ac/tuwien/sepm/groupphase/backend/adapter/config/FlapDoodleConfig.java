package at.ac.tuwien.sepm.groupphase.backend.adapter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlapDoodleConfig {
    private static final Logger logger = LoggerFactory.getLogger(FlapDoodleConfig.class);
    private static final int DB_PORT = 3306;
    private String serverPort;
    private String activeProfiles;
}
