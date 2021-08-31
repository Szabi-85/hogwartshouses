package hogwartshouses.configuration;

import hogwartshouses.dao.*;
import hogwartshouses.dao.implementations.db.*;
import hogwartshouses.dao.implementations.jpa.*;
import hogwartshouses.dao.implementations.mem.*;
import hogwartshouses.model.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class DaoConfiguration {
    @Value("${dao_impl}") String daoImplementation;

    @Bean
    @Primary
    public RoomDao getRoomDao(JdbcTemplate jdbcTemplate, RowMapper<RoomEntity> roomMapper, RowMapper<StudentEntity> studentMapper, ModelMapper modelMapper){
        System.out.print("GET ROOM DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new RoomDaoJdbcImpl(jdbcTemplate, roomMapper, studentMapper, modelMapper);
            case "jpa":
                System.out.println("jpa");
                return new RoomDaoJpa();
            default:
                System.out.println("memory");
                return new RoomDaoMem();
        }
    }

    @Bean
    @Primary
    public StudentDao getStudentDao(JdbcTemplate jdbcTemplate, RowMapper<StudentEntity> studentMapper, RowMapper<RoomEntity> roomMapper) {
        System.out.print("GET STUDENT DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new StudentDaoJdbcImpl(jdbcTemplate, studentMapper, roomMapper);
            case "jpa":
                System.out.println("jpa");
                return new StudentDaoJpa();
            default:
                System.out.println("memory");
                return new StudentDaoMem();
        }
    }

    @Bean
    @Primary
    public RecipeDao getRecipeDao(JdbcTemplate jdbcTemplate, RowMapper<RecipeEntity> recipeMapper) {
        System.out.print("GET RECIPE DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new RecipeDaoJdbcImpl(jdbcTemplate, recipeMapper);
            case "jpa":
                System.out.println("jpa");
                return new RecipeDaoJpa();
            default:
                System.out.println("memory");
                return new RecipeDaoMem();
        }
    }

    @Bean
    @Primary
    public BuildingDao getBuildingDao(JdbcTemplate jdbcTemplate, RowMapper<BuildingEntity> buildingMapper) {
        System.out.print("GET BUILDING DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new BuildingDaoJdbcImpl(jdbcTemplate, buildingMapper);
            case "jpa":
                System.out.println("jpa");
                return new BuildingDaoJpa();
            default:
                System.out.println("memory");
                return new BuildingDaoMem();
        }
    }

    @Bean
    @Primary
    public SpellDao getSpellDao() {
        System.out.print("GET SPELL DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new SpellDaoJdbcImpl();
            case "jpa":
                System.out.println("jpa");
                return new SpellDaoJpa();
            default:
                System.out.println("memory");
                return new SpellDaoMem();
        }
    }

    @Bean
    @Primary
    public TeacherDao getTeacherDao(JdbcTemplate jdbcTemplate, RowMapper<TeacherEntity> teacherMapper) {
        System.out.print("GET TEACHER DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new TeacherDaoJdbcImpl(jdbcTemplate, teacherMapper);
            case "jpa":
                System.out.println("jpa");
                return new TeacherDaoJpa();
            default:
                System.out.println("memory");
                return new TeacherDaoMem();
        }
    }

    @Bean
    @Primary
    public WandDao getWandDao(JdbcTemplate jdbcTemplate, RowMapper<WandEntity> wandMapper, RowMapper<SpellEntity> spellMapper) {
        System.out.print("GET WAND DAO... mode: ");

        switch (daoImplementation) {
            case "jdbc":
                System.out.println("jdbc");
                return new WandDaoJdbcImpl(jdbcTemplate, wandMapper, spellMapper);
            case "jpa":
                System.out.println("jpa");
                return new WandDaoJpa();
            default:
                System.out.println("memory");
                return new WandDaoMem();
        }
    }
}