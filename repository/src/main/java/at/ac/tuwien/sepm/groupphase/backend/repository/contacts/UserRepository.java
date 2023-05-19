package at.ac.tuwien.sepm.groupphase.backend.repository.contacts;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserRepository extends UserRepositoryBase<User, UserMapper, UserDTO> {

}
