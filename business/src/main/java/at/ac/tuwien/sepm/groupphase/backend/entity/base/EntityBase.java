package at.ac.tuwien.sepm.groupphase.backend.entity.base;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.mapstruct.BeanMapping;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Transient;

@Access(AccessType.FIELD)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EntityBase{

    @JsonIgnore
    @Transient
    private MapperBase mapper;

    @JsonIgnore
    @Transient
    private EntityBase entity;

    @JsonIgnore
    @Transient
    private DTOBase dto;

/*
    @JsonIgnore
    @Transient
    private UserRepositoryBase repository;
    */
}
