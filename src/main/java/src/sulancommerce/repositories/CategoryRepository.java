package src.sulancommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT cat FROM Category cat WHERE upper(cat.categoryName) like :filter or CAST(cat.categoryId AS STRING) like :filter")
    List<Category> getCategoriesByNameOrId(@Param("filter") String filter);


    @Query("SELECT count(cat.categoryId) FROM Category cat WHERE upper(cat.categoryName) like :categoryName AND (:categoryId IS NULL OR cat.categoryId <> :categoryId)")
    int existCategoryByCategoryName(@Param("categoryName") String categoryName, @Param("categoryId") Long categoryId);

    @Query("SELECT cat FROM Category cat WHERE upper(cat.categoryName) like :filter OR CAST(cat.categoryId AS STRING) like :filter ORDER BY cat.nrOrder ASC")
    Page<Category> getCategoriesByFilterPaged(String filter, Pageable pageable);

    @Query("SELECT cat FROM Category cat ORDER BY cat.nrOrder ASC")
    List<Category> getAllCategoriesOrderByNrOrder();
}
