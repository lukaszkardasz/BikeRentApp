package com.n2god.bikeRentApp;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Transactional
    public void add(NewBikeDto newBike){
        Bike bike = new Bike(newBike.getId(), newBike.getModel(), newBike.getSerialNo(), newBike.getHourPrice(), newBike.getDayPrice());
            bikeRepository.save(bike);
    }

    @Transactional
    public void deleteById(Long bikeId){
        bikeRepository.deleteById(bikeId);
    }

    @Transactional
    public double rentForHours(Long bikeId, int hours, String borrowerId){
        LocalDateTime dateOfReturn = LocalDateTime.now().plusHours(hours);
        double hourPrice = updateBike(bikeId, dateOfReturn, borrowerId).getHourPrice();
        return hourPrice * hours;
    }

    @Transactional
    public double rentForDays(Long bikeId, int days, String borrowerId){
        LocalDateTime dateOfReturn = LocalDateTime.now().plusDays(days);
        return updateBike(bikeId, dateOfReturn, borrowerId).getDayPrice() * days;
    }

    @Transactional
    public void returnBike(Long bikeId){
        updateBike(bikeId, null, null);
    }


    private Bike updateBike(Long bikeId, LocalDateTime dateOfReturn, String borrowerId){
        Bike bike = bikeRepository
                .findById(bikeId)
                .orElseThrow( BikeNotFoundException::new);
        bike.setDateOfReturn(dateOfReturn);
        bike.setBorrowerId(borrowerId);
        return bike;
    }
}
