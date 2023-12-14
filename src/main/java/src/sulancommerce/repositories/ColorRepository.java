package src.sulancommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query("SELECT col FROM Color col WHERE upper(col.colorName) like :filter OR CAST(col.colorId AS string) like :filter")
    Page<Color> getColorsByFilterPaged(String filter, Pageable pageable);
}
