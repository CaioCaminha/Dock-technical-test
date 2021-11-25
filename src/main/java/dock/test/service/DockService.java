package dock.test.service;

import dock.test.dto.DockDto;
import org.springframework.http.ResponseEntity;

public interface DockService{

    public ResponseEntity createDock(String dock);

    public ResponseEntity getDock(Integer logic);

    public ResponseEntity<DockDto> updateDock(Integer logic);
}
