package hogwartshouses.dao.mapper.repositoryMapper;

import hogwartshouses.model.DTO.RoomDTO;
import hogwartshouses.model.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomDaoRepositoryMapper {
    RoomDaoRepositoryMapper INSTANCE = Mappers.getMapper(RoomDaoRepositoryMapper.class);
    RoomDTO toDto(RoomEntity roomEntity);
    RoomEntity toEntity(RoomDTO roomDTO);
}