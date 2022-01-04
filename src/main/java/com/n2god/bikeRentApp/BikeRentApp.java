package com.n2god.bikeRentApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BikeRentApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BikeRentApp.class, args);

/*
        Bike bike1 = new Bike(1L, "Kross Esker 4.0, 29 cali męski", "KRS12345", 30, 100);
        BikeRepository bikeRepository = context.getBean(BikeRepository.class);
        bikeRepository.save(bike1);
        System.out.println("Zapisano w bazie: " + bike1.getModel());
        Bike bike2 = new Bike(2L, "Trek Marlin 7, 26 cali damski", "TMAR98765", 25, 80);
        bikeRepository.save(bike2);
        System.out.println("Zapisano w bazie: " + bike2.getModel());
        System.out.println("Odczyt z bazy bike 1");
        bikeRepository.findById(1L).ifPresent(System.out::println);
        System.out.println("Odczyt z bazy bike 2");
        bikeRepository.findById(2L).ifPresent(System.out::println);
        System.out.println("Usuwam z bazy bike 1");
        bikeRepository.delete(1L);
        System.out.println("Odczyt z bazy bike 1");
        //bikeRepository.findById(1L).ifPresentOrElse(System.out::println, () -> System.out.println("Brak roweru z id 1L"));
        bikeRepository.findById(1L).orElseThrow(() -> new RuntimeException("bike doesnt exist"));
*/
        NewBikeDto bike1 = new NewBikeDto(1L, "Kross Esker 4.0, 29 cali męski", "KRS12345", 30, 100);
        BikeService bikeService = context.getBean(BikeService.class);
        bikeService.add(bike1);
        bike1 = new NewBikeDto(1L, "New Kross", "ABC999", 10, 90); //bikeId
        bikeService.add(bike1);
        NewBikeDto bike2 = new NewBikeDto(2L, "HORN Speeder 3, 27 cali męski", "FSO52345", 50, 150);
        bikeService.add(bike2);
        double payment = bikeService.rentForHours(1L, 2, "borrowerId_01");
        System.out.printf("Należność za wypożyczenie roweru o id: %s wynosi: %.2f\n", bike1.getId(), payment);
        bikeService.returnBike(1L);
    }
}
