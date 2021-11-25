package dock.controller;


import dock.dto.DockDto;
import dock.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/dock", produces = MediaType.APPLICATION_JSON_VALUE)
public class DockController {

    @Autowired
    private DockService dockService;

    @PostMapping(consumes = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity createDock(@RequestBody String dock){
        return dockService.createDock(dock);
    }

    @PutMapping(value = "/{logic}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DockDto> updateDock(@PathVariable("logic") Integer logic,
                                              @RequestBody @Valid DockDto dockDto){
        return this.dockService.updateDock(logic, dockDto);
    }

    @GetMapping("/{logic}")
    public ResponseEntity<DockDto> getDock(@PathVariable("logic") Integer logic){
        return this.dockService.getDock(logic);
    }

    @GetMapping
    public ResponseEntity<DockDto> getDock(@PageableDefault(sort = "logic",
                                                            direction = Sort.Direction.ASC,
                                                            page = 0, size = 10) Pageable pageable){
        return this.dockService.getAllDocks(pageable);
    }



}
