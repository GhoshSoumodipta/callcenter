package at.ac.tuwien.sepm.groupphase.backend.factories.repository;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

import at.ac.tuwien.sepm.groupphase.backend.repository.common.RepositoryMapper;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

import java.io.Serializable;

public class MyDefaultRepositoryFactory<T, I, M, DTO extends Serializable> extends JpaRepositoryFactory {
	private final PasswordEncoder passwordEncoder;
    private final JpaMetamodelEntityInformation information;
    public MyDefaultRepositoryFactory(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        super(entityManager);
        this.passwordEncoder = passwordEncoder;
        this.information = null;
    }
    /**
     * Simple jpa executor factory constructor
     *
     * @param entityManager entity manager
     */
//    public MyDefaultRepositoryFactory(JpaMetamodelEntityInformation information, EntityManager entityManager) {
//        super(entityManager);
//        this.information = information;
//    }

    @Override
    protected SimpleJpaRepository<T, I> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        JpaEntityInformation entityInformation =
            getEntityInformation(information.getDomainType());
        return new MySimpleRepositoryImpl<T, I, M, DTO>(entityInformation, entityManager, passwordEncoder);
    }

    @Override
    protected Class getRepositoryBaseClass(RepositoryMetadata metadata) {
        return MySimpleRepositoryImpl.class;
    }
    /**
     * Returns whether the given repository interface requires a QueryDsl
     * specific implementation to be chosen.
     *
     * @param repositoryInterface
     * @return
     */
    private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
        if(repositoryInterface.isAssignableFrom(RepositoryMapper.class)){
            return false;
        }
        return QUERY_DSL_PRESENT
            && QuerydslPredicateExecutor.class
            .isAssignableFrom(repositoryInterface);
    }

    private CrudMethodMetadata matadata;
}