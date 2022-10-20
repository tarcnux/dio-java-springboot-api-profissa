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
		var id_1 = getUUID();		
		var parking_1 = new Parking(id_1, "DMS-1122", "SC", "CELTA", "PRETO");		
		parkingMap.put(id_1, parking_1);
		
		var id_2 = getUUID();
		var parking_2 = new Parking(id_2, "MST-6666", "SP", "FORD KA", "AZUL");
		parkingMap.put(id_2, parking_2);
		
		var id_3 = getUUID();
		var parking_3 = new Parking(id_3, "XPT-1313", "MG", "FUSCA", "VERMELHO");
		parkingMap.put(id_3, parking_3);
	}
	
	public List<Parking> findAll() {
		System.out.println("ParkingService findAll");
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	private static String getUUID() {		
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		System.out.println("ParkingService findById");
		return parkingMap.get(id);
	}

}
