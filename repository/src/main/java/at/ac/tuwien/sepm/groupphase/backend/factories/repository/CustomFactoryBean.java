package at.ac.tuwien.sepm.groupphase.backend.factories.repository;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.util.TxUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.Serializable;

public class CustomFactoryBean<R extends MySimpleRepositoryImpl<T, I, M, DTO>, T, I, M, DTO extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    private String transactionManagerName = TxUtils.DEFAULT_TRANSACTION_MANAGER;

    private BeanFactory beanFactory;
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public CustomFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
   public void setTransactionManager(String transactionManager) {
           super.setTransactionManager(transactionManager);
           this.transactionManagerName = transactionManager;
   }

       @Override
   public void setBeanFactory(BeanFactory beanFactory) {
           super.setBeanFactory(beanFactory);
           this.beanFactory = beanFactory;
   }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        this.beanFactory.getBean(this.transactionManagerName, PlatformTransactionManager.class);
        return new MyDefaultRepositoryFactory<T, I, M, DTO>(entityManager, passwordEncoder);
    }

    /**
     * Simple jpa executor factory
     *
     * @param <T>
     * @param <I>
     */
}