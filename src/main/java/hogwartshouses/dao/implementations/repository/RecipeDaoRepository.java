package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeDaoRepository extends CrudRepository<RecipeEntity, Long> {
}