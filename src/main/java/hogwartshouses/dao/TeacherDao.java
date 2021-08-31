package hogwartshouses.dao;

import hogwartshouses.model.entity.TeacherEntity;

import java.util.List;

public interface TeacherDao {
    List<TeacherEntity> listAllTeacher();
    TeacherEntity getTeacher(Long id);
    void addTeacher(TeacherEntity teacher);
    void updateTeacher(Long id, TeacherEntity teacher);
    void deleteTeacher(Long id);
    List<TeacherEntity> listTeacherByWandWoodType(String woodType);
}