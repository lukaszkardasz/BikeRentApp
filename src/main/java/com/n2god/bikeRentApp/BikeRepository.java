package com.n2god.bikeRentApp;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class BikeRepository {
    private final EntityManager entityManager;


    public BikeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Bike bike){
        entityManager.persist(bike);
    }

    @Transactional
    public Optional<Bike> findById(Long id){
        return Optional.ofNullable(entityManager.find(Bike.class, id));
    }

    @Transactional
    public void delete(Long id){
        findById(id).ifPresent(entityManager::remove);
    }

    @Transactional
    public void deleteById(Long bikeId){
        Optional<Bike> bikeById = findById(bikeId);
        if(bikeById.isPresent()){
            Bike bike = bikeById.get();
            entityManager.remove(bike);
        }
    }
}
