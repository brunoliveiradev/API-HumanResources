package dev.brunoliveira.hrworker.repositories;

import dev.brunoliveira.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
