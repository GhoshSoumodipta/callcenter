package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.usertype;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.usertype.UserTypeDTO;

import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserTypeMapper extends MapperBase<UserType, UserTypeDTO> {

    /**
     * Maps User object to UserDTO object
     * @param userType to map
     * @return the mapped UserDTO object
     */
    default UserTypeDTO entityToDTO(UserType userType) {
        return userType.getDTOByName();
    }
    /**
     * Maps UserDTO object to User object
     * @param userTypeDTO to map
     * @return the mapped User object
     */
    default UserType DTOToEntity(UserTypeDTO userTypeDTO){
        return UserType.getTypeByName(userTypeDTO.getName());
    }
}
