package dock.test.controller;


import dock.test.dto.DockDto;
import dock.test.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/dock", produces = MediaType.APPLICATION_JSON_VALUE)
public class DockController {

    @Autowired
    private DockService dockService;

    @PostMapping(consumes = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity createDock(@RequestBody String dock){
        return dockService.createDock(dock);
    }

    @PutMapping("/{logic}")
    public ResponseEntity<DockDto> updateDock(@PathVariable("logic") Integer logic){
        return null;
    }

    @GetMapping("/{logic}")
    public ResponseEntity<DockDto> getDock(@PathVariable("logic") Integer logic){
        return this.dockService.getDock(logic);
    }



}
