package dev.brunoliveira.hrworker.controllers;

import dev.brunoliveira.hrworker.dto.WorkerDto;
import dev.brunoliveira.hrworker.service.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public ResponseEntity<List<WorkerDto>> findAll() {
        List<WorkerDto> workers = workerService.findAll();
        return ResponseEntity.ok().body(workers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDto> findById(@PathVariable Long id) {
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
