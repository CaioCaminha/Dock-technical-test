package dock.test.service.impl;


import dock.test.repository.DockRepository;
import dock.test.service.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DockServiceImpl implements DockService {

    @Autowired
    private DockRepository dockRepository;




}
