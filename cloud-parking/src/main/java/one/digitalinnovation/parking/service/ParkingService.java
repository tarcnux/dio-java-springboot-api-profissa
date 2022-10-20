package one.digitalinnovation.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;

@Service
public class ParkingService {
	
	private static Map<String, Parking> parkingMap = new HashMap<String, Parking>();
	
	//Mock
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
		var parkingList = parkingMap.values().stream().collect(Collectors.toList());
		parkingList.forEach(System.out::println); //method reference
		return parkingList;
	}

	private static String getUUID() {
		var uuid =  UUID.randomUUID().toString().replace("-", "");
		System.out.println("ParkingService getUUID uudi: " + uuid);
		return uuid;
	}

	public Parking findById(String id) {
		System.out.println("ParkingService findById");
		var parkingResult = parkingMap.get(id);	
		if(parkingResult == null) {
			throw new ParkingNotFoundException(id);
		}
		System.out.println("ParkingService findById parkingResult: " + parkingResult);
		return parkingResult;
	}

	public Parking create(Parking parkingCreate) {
		System.out.println("ParkingService create");
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		System.out.println("ParkingService create parkingCreate:\n" + parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
		System.out.println("ParkingService create");
		var parking = findById(id);
		parkingMap.remove(parking.getId());
	}

	public Parking update(String id, Parking parkingCreate) {
		var parking = findById(id);
		parking.setColor(parkingCreate.getColor()); //just change the color
		parkingMap.replace(id, parking);
		return parking;
	}
	
	public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingMap.replace(id, parking);
        return parking;
    }

}
