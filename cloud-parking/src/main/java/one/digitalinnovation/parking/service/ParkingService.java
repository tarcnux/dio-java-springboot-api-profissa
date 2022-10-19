package one.digitalinnovation.parking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import one.digitalinnovation.parking.model.Parking;

@Service
public class ParkingService {
	
	private static Map<String, Parking> parkingMap = new HashMap<String, Parking>();
	
	static {
		var id = getUUID();
		var parking = new Parking(id, "DMS-1122", "SC", "CELTA", "PRETO");
		parkingMap.put(id, parking);		
	}
	
	public List<Parking> findAll() {
		System.out.println("ParkingService findAll");
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	private static String getUUID() {		
		return UUID.randomUUID().toString().replace("-", "");
	}

}