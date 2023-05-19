package at.ac.tuwien.sepm.groupphase.backend;

import at.ac.tuwien.sepm.groupphase.backend.factories.repository.CustomFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages="at.ac.tuwien.sepm.groupphase.backend")
@EntityScan(basePackages = {"at.ac.tuwien.sepm.groupphase.backend.entity", "at.ac.tuwien.sepm.groupphase.backend.calling.entity"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
