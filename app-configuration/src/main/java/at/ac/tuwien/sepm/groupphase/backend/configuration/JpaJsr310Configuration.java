package at.ac.tuwien.sepm.groupphase.backend.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Configuration
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class})
public class JpaJsr310Configuration {

}
