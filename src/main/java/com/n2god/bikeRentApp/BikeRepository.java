package com.n2god.bikeRentApp;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class BikeRepository {
    private final EntityManager entityManager;

    public BikeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Bike bike){
        entityManager.persist(bike);
    }

    public void update(Bike bike){
        entityManager.merge(bike);
    }

    public Optional<Bike> findById(Long id){
        return Optional.ofNullable(entityManager.find(Bike.class, id));
    }


    public void deleteById(Long id){
        findById(id).ifPresent(entityManager::remove);
    }

//    public void deleteById(Long bikeId){
//        Optional<Bike> bikeById = findById(bikeId);
//        if(bikeById.isPresent()){
//            Bike bike = bikeById.get();
//            entityManager.remove(bike);
//        }
//    }

}
