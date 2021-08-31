package hogwartshouses.service;

import hogwartshouses.dao.StudentDao;
import hogwartshouses.dao.RoomDao;
import hogwartshouses.model.DTO.RoomDTO;
import hogwartshouses.model.DTO.StudentDTO;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.PetType;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomDao roomDao;
    private final StudentDao studentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomDao roomDaoJdbcImpl, StudentDao studentDaoJdbcImpl) {
        this.roomDao = roomDaoJdbcImpl;
        this.studentDao = studentDaoJdbcImpl;
    }

    public List<RoomDTO> findAll() {
        List<RoomEntity> rooms = new ArrayList<>();
        roomDao.listRooms().forEach(rooms::add);
        List<RoomDTO> roomDTOs = new ArrayList<>();
        rooms.forEach(room -> roomDTOs.add(toRoomDTO(room)));
        return roomDTOs;
    }

    public RoomDTO findRoomById(long id) {
        return toRoomDTO(roomDao.findRoomById(id));
    }

    public RoomDTO saveRoom(RoomEntity roomEntity) {
        roomDao.addRoom(roomEntity);
        return toRoomDTO(roomEntity);
    }

    public void deleteRoomById(long id) {
        roomDao.deleteRoomById(id);
    }

    public List<RoomDTO> findAvailableRooms() {
        List<RoomDTO> rooms = findAll().stream()
                .filter(RoomDTO::isHasEmptyBed)
                .collect(Collectors.toList());
        return rooms;
    }

    public List<StudentDTO> findAllResidentOfARoomByRoomId(Long roomId) {
        RoomDTO roomDTO = findRoomById(roomId);
        Set<StudentEntity> residents = roomDTO.getStudentEntities();
        List<StudentEntity> residentList = new ArrayList<>();
        residentList.addAll(residents);
        List<StudentDTO> residentDTOs = new ArrayList<>();
        residentList.forEach(student -> residentDTOs.add(toStudentDto(student)));
        return residentDTOs;
    }

    public List<RoomDTO> findRoomWithNoCatOrOwl() {
        return findAvailableRooms().stream()
                .filter(roomDTO -> findAllResidentOfARoomByRoomId(roomDTO.getId()).stream()
                        .map(student -> !student.getPetType().equals(PetType.CAT) && !student.getPetType().equals(PetType.OWL))
                        .filter(value -> !value)
                        .findFirst()
                        .orElse(Boolean.TRUE))
                .collect(Collectors.toList());
    }

    public void addStudentToRoom(long roomId, StudentEntity studentEntity) {
        RoomEntity roomEntity = roomDao.findRoomById(roomId);
        if (roomEntity.isHasEmptyBed()) {
            studentDao.connectStudentAndRoom(studentEntity, roomEntity);
            updateRoom(roomEntity);
            roomDao.updateRoomById(roomEntity.getId(), roomEntity);
        }
    }

    public RoomDTO toRoomDTO(RoomEntity roomEntity) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        RoomDTO roomDto = modelMapper.map(roomEntity, RoomDTO.class);
        return roomDto;
    }

    public RoomEntity toEntity(RoomDTO roomDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        RoomEntity roomEntity = modelMapper.map(roomDTO, RoomEntity.class);
        return roomEntity;
    }

    public StudentDTO toStudentDto(StudentEntity studentEntity) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
        return studentDTO;
    }

    private void updateRoom(RoomEntity roomEntity) {
        roomEntity.setNumberOfBeds(roomEntity.getCapacity() - (roomEntity.getStudentEntities() == null ? 0 : roomEntity.getStudentEntities().size()));
        roomEntity.setHasEmptyBed(roomEntity.getNumberOfBeds() > 0);
    }
}