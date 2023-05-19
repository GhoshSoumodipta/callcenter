package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends MapperBase<User, UserDTO> {

    /**
     * Maps User object to UserDTO object
     * @param user to map
     * @return the mapped UserDTO object
     */
    UserDTO entityToDTO(User user);

    /**
     * Maps UserDTO object to User object
     * @param userGetDTO to map
     * @return the mapped User object
     */
    User DTOToEntity(UserDTO userGetDTO);
}
