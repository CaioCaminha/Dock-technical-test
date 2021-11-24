package dock.test.service.impl;


import dock.test.dto.DockDto;
import dock.test.entity.DockEntity;
import dock.test.repository.DockRepository;
import dock.test.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class DockServiceImpl implements DockService {

    @Autowired
    private DockRepository dockRepository;


    @Override
    public ResponseEntity createDock(String dock) {
        try{
            DockDto dockDto = DockDto.generateDto(dock);
            DockEntity dockEntity = this.dockRepository.save(new DockEntity(dockDto));
            return ResponseEntity.created(new URI(dockEntity.getId().toString())).body(dockDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("erro ao cadastrar entidade");
        }
    }

    @Override
    public ResponseEntity<DockDto> getDock(Integer logic) {
        return null;
    }

    @Override
    public ResponseEntity<DockDto> updateDock(Integer logic) {
        return null;
    }
}
