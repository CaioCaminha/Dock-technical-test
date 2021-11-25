package dock.service.impl;


import dock.json.JsonSchemaValidator;
import dock.service.DockService;
import dock.dto.DockDto;
import dock.entity.DockEntity;
import dock.repository.DockRepository;
import dock.response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DockServiceImpl implements DockService {

    @Autowired
    private DockRepository dockRepository;

    @Autowired
    private ResponseService response;

    @Autowired
    private JsonSchemaValidator jsonSchemaValidator;


    @Override
    public ResponseEntity createDock(String dock) {
        try{
            DockDto dockDto = DockDto.generateDto(dock);
            if(this.jsonSchemaValidator.isJsonValid(dockDto)){
                DockEntity dockEntity = this.dockRepository.save(new DockEntity(dockDto));
                return response.success(dockDto, HttpStatus.CREATED);
            }else{
                return response.error("Erro ao cadastrar entidade", "Json não segue estrutura exigida", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return response.error("Erro ao cadastrar entidade", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getDock(Integer logic) {
        try{
            Optional<DockEntity> optionalDockEntity = this.dockRepository.findById(logic);
            if(optionalDockEntity.isPresent()){
                return response.success(optionalDockEntity.get(), HttpStatus.OK);
            }else{
                return response.error("Erro ao buscar entidade", "logic: " + logic, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return response.error("Erro ao buscar entidade", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getAllDocks(Pageable pageable) {
        try{
            return response.success(this.dockRepository.findAll(pageable), HttpStatus.OK);
        }catch (Exception e){
            return response.error("Não foi possível buscar entidades", e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateDock(Integer logic, DockDto dockDto) {
        try{
            if(this.jsonSchemaValidator.isJsonValid(dockDto)){
                Optional<DockEntity> optionalDockEntity = this.dockRepository.findById(logic);
                if(optionalDockEntity.isPresent()){
                    DockEntity dockEntity = this.update(dockDto, optionalDockEntity.get());
                    return response.success(this.dockRepository.save(dockEntity), HttpStatus.OK);
                }
                return response.error("Não foi possível atualizar entidade", "Entidade não localizada | logic: " + logic, HttpStatus.BAD_REQUEST);
            }
            return response.error("Não foi possível atualizar entidade", "Json não segue estrutura exigida", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return response.error("Não foi possível atualizar a entidade", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private DockEntity update(DockDto dockDto, DockEntity dockEntity){
            dockEntity.setSerial(dockDto.getSerial());
            dockEntity.setModel(dockDto.getModel());
            dockEntity.setSam(dockDto.getSam());
            dockEntity.setPtid(dockDto.getPtid());
            dockEntity.setPlat(dockDto.getPlat());
            dockEntity.setVersion(dockDto.getVersion());
            dockEntity.setMxr(dockDto.getMxr());
            dockEntity.setMxf(dockDto.getMxf());
            dockEntity.setPVERFM(dockDto.getPVERFM());
            return dockEntity;
    }

}
