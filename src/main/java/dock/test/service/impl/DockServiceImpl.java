package dock.test.service.impl;


import dock.test.dto.DockDto;
import dock.test.entity.DockEntity;
import dock.test.repository.DockRepository;
import dock.test.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class DockServiceImpl implements DockService {

    @Autowired
    private DockRepository dockRepository;


    @Override
    public ResponseEntity createDock(String dock) {
        try{
            DockDto dockDto = DockDto.generateDto(dock);
            DockEntity dockEntity = this.dockRepository.save(new DockEntity(dockDto));
            return ResponseEntity.created(new URI(dockEntity.getLogic().toString())).body(dockDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("erro ao cadastrar entidade");
        }
    }

    @Override
    public ResponseEntity getDock(Integer logic) {
        try{
            Optional<DockEntity> optionalDockEntity = this.dockRepository.findById(logic);
            if(optionalDockEntity.isPresent()){
                return ResponseEntity.ok(new DockDto(optionalDockEntity.get()));
            }else{
                return ResponseEntity.badRequest().body("Erro ao buscar entidade");
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Erro ao buscar entidade");
        }
    }

    @Override
    public ResponseEntity<DockDto> updateDock(Integer logic) {
        try{
            Optional<DockEntity> optionalDockEntity = this.dockRepository.findById(logic);
            if(optionalDockEntity.isPresent()){
                DockEntity dockEntity = optionalDockEntity.get();
                return null;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
