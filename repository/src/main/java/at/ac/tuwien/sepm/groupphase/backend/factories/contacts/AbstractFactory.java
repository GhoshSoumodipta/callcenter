package at.ac.tuwien.sepm.groupphase.backend.factories.contacts;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Proxy;

public abstract class AbstractFactory {
    abstract MyJpaRepository getRepository(UserType userType);

    @SuppressWarnings({"unchecked"})
    protected <T> T getTargetObject(Object proxy, Class<T> targetClass) throws Exception {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return (T) ((Advised)proxy).getTargetSource().getTarget();
        } else {
            return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
        }
    }
}
