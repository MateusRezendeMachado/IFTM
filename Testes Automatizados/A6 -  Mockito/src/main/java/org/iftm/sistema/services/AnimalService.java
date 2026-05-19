package org.iftm.sistema.services;

import org.iftm.sistema.entities.Animal;
import org.iftm.sistema.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    private static final List<String> ESPECIES_VALIDAS = List.of("Cachorro", "Gato", "Papagaio");

    // Cadastrar com internado = true e validação de espécie
    public Animal cadastrar(Animal animal) {
        if (!ESPECIES_VALIDAS.contains(animal.getEspecie())) {
            throw new IllegalArgumentException("Espécie não atendida pela clínica");
        }
        animal.setInternado(true);
        return repository.save(animal);
    }

    // Buscar por espécie
    public List<Animal> buscarPorEspecie(String especie) {
        return repository.findByEspecie(especie);
    }

    // Dar alta (desinternar)
    public void darAlta(Long id) {
        Animal animal = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        animal.setInternado(false);
        repository.save(animal);
    }

    // Deletar com validação
    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Animal não encontrado");
        }
        repository.deleteById(id);
    }
}