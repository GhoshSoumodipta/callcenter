package at.ac.tuwien.sepm.groupphase.backend.repository.implementation;

import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts_;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User_;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MySimpleRepositoryImpl;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class UserRepositoryBaseImpl<S extends User, M, DTO extends Serializable> extends MySimpleRepositoryImpl<S, Long, M, DTO> implements UserRepositoryBase<S, M, DTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryBaseImpl.class);

    @Autowired
    public UserRepositoryBaseImpl(JpaEntityInformation entityInformation, EntityManager entityManager, PasswordEncoder passwordEncoder) {
        super(entityInformation, entityManager, passwordEncoder);
    }

//    @Override
    public List findAllBlocked() {
        LOGGER.debug("UserRepositoryImpl: findAllBlockedUsers");
        CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
        //Collection of conditions
        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<User> cq = cBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(getEntityManager().getMetamodel().entity(User.class));
        Join<User, LoginAttempts> userLoginAttemptsJoin = userRoot.join(User_.LOGIN_ATTEMPTS);
        predicates.add(cBuilder.isTrue(userLoginAttemptsJoin.get(LoginAttempts_.BLOCKED)));
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        cq.select(userRoot);
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public Optional<S> findOneByUserId(Long id){
        User user = User.builder().userId(id).build();
        Example<User> userExample = Example.of(user);
        return findOne((Example<S>) userExample);
    }

    private static boolean isUnpaged(Pageable pageable) {
        return pageable.isUnpaged();
    }

    /**
     * Find all customers and order the list by the ID ascending.
     *
     * @param pageable special parameter to apply pagination
     * @return a page of the found customers
     */
    @Override
    public Page<User> findAllByOrderByUserIdAsc(Pageable pageable){
//        return (Page)(isUnpaged(pageable) ? new PageImpl(this.findAll()) : this.findAll((Specification)null, pageable));
        return new PageImpl(findAll(Sort.by("userId").ascending()));
    }


    /**
     * Find user entry by username.
     *
     * @param username the username of the desired user
     * @return Optional containing the user
     */

    //TODO check
    //@Query("Select u from #{#entityName} u Where u.username =?1")
    @Override
    public Optional<S> findOneByUsername(String username){
        User user = User.builder().username(username).build();
        Example<User> userExample = Example.of(user);
        return findOne((Example<S>) userExample);
    }

    /**
     * Get all users whose name contains the string 'username'
     *
     * @return list of users
     */
    @Override
    public Page<S> findByUsernameContainingIgnoreCase(String username, Pageable pageable){
        CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<User> criteriaQuery = cBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);

        if (username != null) {
            predicates.add(cBuilder.like(cBuilder.lower(user.get(User_.username)), "%" + username.toLowerCase() + "%"));
        }

        criteriaQuery.select(user).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(criteriaQuery);

        List<User> locationList = typedQuery.getResultList();
        if (locationList.isEmpty()) {
            throw new NotFoundException("No locations are found with those parameters");
        }

        int totalElements = locationList.size();

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());
        locationList = typedQuery.getResultList();

        return (Page<S>) new PageImpl<User>(locationList, pageable, totalElements);
    }

    @Override
    public Page<S> findByUserIdAndFirstNameAndLastNameAndEmailsAndBirthday(Long userId, String firstName, String lastName, String email, LocalDate birthday, Pageable pageable){
        CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<User> criteriaQuery = cBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);

        if (userId != null) {
            predicates.add(cBuilder.equal(user.get(User_.userId), userId));
        }
        if (firstName != null) {
            predicates.add(cBuilder.like(cBuilder.lower(user.get(User_.firstName)), "%" + firstName.toLowerCase() + "%"));
        }

        if (lastName != null) {
            predicates.add(cBuilder.like(cBuilder.lower(user.get(User_.lastName)), "%" + lastName.toLowerCase() + "%"));
        }

        if (email != null) {
            predicates.add(cBuilder.like(cBuilder.lower(user.get(User_.username)), "%" + email.toLowerCase() + "%"));
        }

        if(birthday!=null){
            predicates.add(cBuilder.equal(user.get(User_.birthday), birthday));
        }

        criteriaQuery.select(user).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(criteriaQuery);

        List<User> locationList = typedQuery.getResultList();
        if (locationList.isEmpty()) {
            throw new NotFoundException("No locations are found with those parameters");
        }

        int totalElements = locationList.size();

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());
        locationList = typedQuery.getResultList();

        return (Page<S>) new PageImpl<User>(locationList, pageable, totalElements);

    }

    public Page<S> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable){
        CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<User> criteriaQuery = cBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);

        if (firstName != null) {
            predicates.add(cBuilder.like(cBuilder.lower(user.get(User_.firstName)), "%" + firstName.toLowerCase() + "%"));
        }

        criteriaQuery.select(user).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(criteriaQuery);

        List<User> locationList = typedQuery.getResultList();
        if (locationList.isEmpty()) {
            throw new NotFoundException("No locations are found with those parameters");
        }

        int totalElements = locationList.size();

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());
        locationList = typedQuery.getResultList();

        return (Page<S>) new PageImpl<User>(locationList, pageable, totalElements);
    }
}
