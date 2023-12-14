package src.sulancommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("SELECT size FROM Size size WHERE upper(size.sizeName) like :filter OR CAST(size.sizeId AS string) like :filter")

    Page<Size> getSizesByFilterPaged(@Param("filter") String filter, Pageable pageable);
}
