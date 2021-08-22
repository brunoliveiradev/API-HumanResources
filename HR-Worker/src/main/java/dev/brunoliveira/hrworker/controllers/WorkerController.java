package dev.brunoliveira.hrworker.controllers;

import dev.brunoliveira.hrworker.dto.WorkerDto;
import dev.brunoliveira.hrworker.service.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
