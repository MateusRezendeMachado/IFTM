// AnimalRepository.java
package org.iftm.sistema.repositories;

import org.iftm.sistema.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByEspecie(String especie);
}