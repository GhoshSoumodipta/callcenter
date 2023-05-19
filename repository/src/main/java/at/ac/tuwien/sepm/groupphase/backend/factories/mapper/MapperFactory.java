package at.ac.tuwien.sepm.groupphase.backend.factories.mapper;

import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MySimpleRepositoryImpl;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;

public class MapperFactory {

    public MapperBase getMapper(MyJpaRepository repositoryBase){
       return (MapperBase) repositoryBase.mapper();
    }
}
