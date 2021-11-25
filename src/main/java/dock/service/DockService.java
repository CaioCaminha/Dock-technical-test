package dock.service;

import dock.dto.DockDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface DockService{

    public ResponseEntity createDock(String dock);

    public ResponseEntity getDock(Integer logic);

    public ResponseEntity getAllDocks(Pageable pageable);

    public ResponseEntity updateDock(Integer logic, DockDto dockDto);
}
