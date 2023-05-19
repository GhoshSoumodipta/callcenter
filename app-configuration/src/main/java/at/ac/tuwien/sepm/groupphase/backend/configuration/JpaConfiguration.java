package at.ac.tuwien.sepm.groupphase.backend.configuration;

import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.OrganizationFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.OrganizationUserFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.RepositoryFactoryMain;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.UserFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.mapper.MapperFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.CustomFactoryBean;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyDefaultRepositoryFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MySimpleRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(value = "at.ac.tuwien.sepm.groupphase.backend.repository",
    repositoryFactoryBeanClass = CustomFactoryBean.class,
    repositoryBaseClass = MyJpaRepository.class)
public class JpaConfiguration {


    @Bean
    RepositoryFactoryMain repositoryFactoryMain(){
        return new RepositoryFactoryMain();
    }

    @Bean
    OrganizationFactory organizationFactory(){
        return new OrganizationFactory();
    }

    @Bean
    OrganizationUserFactory organizationUserFactory(){
        return new OrganizationUserFactory();
    }

    @Bean
    UserFactory userFactory(){
        return new UserFactory();
    }

    @Bean
    MapperFactory mapperFactory(){ return new MapperFactory(); }
}
