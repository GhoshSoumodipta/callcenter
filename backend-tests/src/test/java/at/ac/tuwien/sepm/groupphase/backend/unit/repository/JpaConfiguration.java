package at.ac.tuwien.sepm.groupphase.backend.unit.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.OrganizationFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.OrganizationUserFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.RepositoryFactoryMain;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.UserFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.mapper.MapperFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.CustomFactoryBean;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;

@TestConfiguration
@EnableJpaRepositories(value = "at.ac.tuwien.sepm.groupphase.backend.repository",
    repositoryFactoryBeanClass = CustomFactoryBean.class,
    repositoryBaseClass = MyJpaRepository.class)
@EntityScan(value = {"at.ac.tuwien.sepm.groupphase.backend.entity"})
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
