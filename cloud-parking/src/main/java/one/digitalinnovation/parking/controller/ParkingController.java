package one.digitalinnovation.parking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	
	private final ParkingService parkingService;
		
	//Autowired is not recommended anymore
	public ParkingController(ParkingService parkingService) {
		this.parkingService = parkingService;
	}



	@GetMapping
	public List<Parking> findAll() {
		
		System.out.println("ParkingController findAll()");

		return parkingService.findAll();
		
	}

}
