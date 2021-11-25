package dock.test.repository;

import dock.test.entity.DockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DockRepository extends JpaRepository<DockEntity, Integer> {

    Optional<DockEntity> findByLogic(Integer logic);


}
