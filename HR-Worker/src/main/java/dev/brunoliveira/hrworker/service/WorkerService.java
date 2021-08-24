package dev.brunoliveira.hrworker.service;

import dev.brunoliveira.hrworker.dto.WorkerDto;
import dev.brunoliveira.hrworker.entities.Worker;
import dev.brunoliveira.hrworker.repositories.WorkerRepository;
import dev.brunoliveira.hrworker.service.exceptions.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional
    public WorkerDto insert(WorkerDto workerDto){
        Worker worker = createWorker(workerDto);
        worker = workerRepository.save(worker);

        return new WorkerDto(worker);
    }

    @Transactional
    public WorkerDto update(WorkerDto workerDto, Long id) {
        try {
            Worker worker = updateWorker(workerDto, id);
            worker = workerRepository.save(worker);

            return new WorkerDto(worker);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("ID: " + id + " not found!");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            workerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("ID: " + id + " not found!");
        }
    }

    private Worker createWorker(WorkerDto worker){
        Worker entity = new Worker();
        entity.setName(worker.getName());
        entity.setDailyIncome(worker.getDailyIncome());
        return entity;
    }

    private Worker updateWorker(WorkerDto worker, Long id){
        Worker entity = workerRepository.getOne(id);
        entity.setName(worker.getName());
        entity.setDailyIncome(worker.getDailyIncome());
        return entity;
    }
}
