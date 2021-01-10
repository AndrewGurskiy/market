package by.andrew.demo.repository;

import by.andrew.demo.entity.parts.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
