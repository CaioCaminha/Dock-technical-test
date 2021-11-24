package dock.test.repository;

import dock.test.entity.DockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DockRepository extends JpaRepository<DockEntity, Integer> {
}
