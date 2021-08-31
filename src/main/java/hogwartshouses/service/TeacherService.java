package hogwartshouses.service;

import hogwartshouses.dao.TeacherDao;
import hogwartshouses.dao.WandDao;
import hogwartshouses.model.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherDao teacherDao;
    private final WandDao wandDao;

    @Autowired
    public TeacherService(TeacherDao teacherDao, WandDao wandDao) {
        this.teacherDao = teacherDao;
        this.wandDao = wandDao;
    }

    public List<TeacherEntity> listAllTeacher() {
        return teacherDao.listAllTeacher();
    }

    public TeacherEntity getTeacher(Long id) {
        return teacherDao.getTeacher(id);
    }

    public void addTeacher(TeacherEntity teacher) {
        if (teacher.getWandEntity() != null && teacher.getWandEntity().getId() != null) {
            try {
                teacher.setWandEntity(wandDao.findById(teacher.getWandEntity().getId()));
                teacherDao.addTeacher(teacher);
            } catch (RuntimeException e) {
            }
        }
    }

    public void updateTeacher(Long id, TeacherEntity teacher) {
        if (teacher.getWandEntity() != null && teacher.getWandEntity().getId() != null) {
            try {
                teacher.setWandEntity(wandDao.findById(teacher.getWandEntity().getId()));
                teacherDao.updateTeacher(id, teacher);
            } catch (RuntimeException e) {
            }
        }
    }

    public void deleteTeacher(Long id) {
        teacherDao.deleteTeacher(id);
    }

    public List<TeacherEntity> listTeacherByWandWoodType(String woodType) {
        return teacherDao.listTeacherByWandWoodType(woodType);
    }
}