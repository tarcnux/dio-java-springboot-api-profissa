package one.digitalinnovation.parking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.parking.model.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	@GetMapping
	public List<Parking> findAll() {
		
		System.out.println("ParkingController findAll()");
		
		var parking_1 = new Parking();
		var parking_2 = new Parking();
		
		var parkingList = new ArrayList<Parking>();
		
		parking_1.setColor("PRETO");
		parking_1.setLicense("MSS-1111");
		parking_1.setModel("VW GOL");
		parking_1.setState("SC");
		
		parking_2.setColor("AZUL");
		parking_2.setLicense("MST-666");
		parking_2.setModel("FORD KA");
		parking_2.setState("MG");
		
		parkingList.add(parking_1);
		parkingList.add(parking_2);
		
		return parkingList;
		
	}

}
