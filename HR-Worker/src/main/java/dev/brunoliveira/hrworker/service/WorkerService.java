package dev.brunoliveira.hrworker.service;

import dev.brunoliveira.hrworker.dto.WorkerDto;
import dev.brunoliveira.hrworker.models.Worker;
import dev.brunoliveira.hrworker.repositories.WorkerRepository;
import dev.brunoliveira.hrworker.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Transactional(readOnly = true)
    public List<WorkerDto> findAll(){
        List<Worker> workers = this.workerRepository.findAll();
        return workers.stream().map(WorkerDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WorkerDto findById(Long id) {
        Optional<Worker> categoryOptional = workerRepository.findById(id);
        Worker entity = categoryOptional.orElseThrow(() ->
                new ResourceNotFoundException("Entity not found"));
        return new WorkerDto(entity);
    }


}
