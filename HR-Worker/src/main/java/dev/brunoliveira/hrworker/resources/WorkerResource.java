package dev.brunoliveira.hrworker.resources;

import dev.brunoliveira.hrworker.dto.WorkerDto;
import dev.brunoliveira.hrworker.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Value("${test.config}")
    private String testConfig;

    private final Environment env;

    private final WorkerService workerService;

    public WorkerResource(WorkerService workerService, Environment env) {
        this.workerService = workerService;
        this.env = env;
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfig() {
        logger.info("CONFIG = {}", testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WorkerDto>> findAll() {
        List<WorkerDto> workers = workerService.findAll();
        return ResponseEntity.ok().body(workers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDto> findById(@PathVariable Long id) {

        logger.info("PORT = {}", env.getProperty("local.server.port"));

        WorkerDto worker = workerService.findById(id);
        return ResponseEntity.ok().body(worker);
    }

    @PostMapping
    public ResponseEntity<WorkerDto> insert(@RequestBody WorkerDto worker){
        worker = workerService.insert(worker);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(worker.getId())
                .toUri();

        return ResponseEntity.created(uri).body(worker);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WorkerDto> update(@PathVariable Long id, @RequestBody WorkerDto worker) {
        worker = workerService.update(worker, id);

        return ResponseEntity.ok().body(worker);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
