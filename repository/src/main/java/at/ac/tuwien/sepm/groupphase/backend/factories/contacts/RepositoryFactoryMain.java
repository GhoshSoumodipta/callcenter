package at.ac.tuwien.sepm.groupphase.backend.factories.contacts;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryFactoryMain {

    @Autowired
    private OrganizationFactory organizationFactory;
    @Autowired
    private OrganizationUserFactory organizationUserFactory;
    @Autowired
    private UserFactory userFactory;

    public UserRepositoryBase getRepository(UserType userType){
        AbstractFactory factory = switch (userType){
            case SWITCHINGCALLCENTER, COMPANY -> organizationFactory;
            case COMPANYLEADER, SWITCHINGCALLCENTERLEADER, COMPANYEMPLOYEE, SWITCHINGCALLCENTEREMPLOYEE -> organizationUserFactory;
            case CAPTIONER, COMMUNICATIONASSISTENCE, CUSTOMER, INTERPRETER -> userFactory;
            default -> throw new IllegalArgumentException("UserType Unknown: "+userType);
        };
        return (UserRepositoryBase) factory.getRepository(userType);

    }
}
