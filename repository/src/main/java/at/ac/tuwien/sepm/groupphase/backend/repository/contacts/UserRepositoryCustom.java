package at.ac.tuwien.sepm.groupphase.backend.repository.contacts;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;

public interface UserRepositoryCustom<T extends User, M, DTO> extends UserRepositoryBase<T, M, DTO> {

    /**
     * Get list of all blocked users.
     *
     * @return a list of all currently blocked users
     */

}
