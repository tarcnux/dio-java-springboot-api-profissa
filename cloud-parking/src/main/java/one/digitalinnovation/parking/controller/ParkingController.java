package one.digitalinnovation.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
	
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
		
	//Autowired is not recommended anymore
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}


	@GetMapping
	@ApiOperation("Find all parkings")
	public ResponseEntity<List<ParkingDTO>> findAll() {		
		System.out.println("ParkingController findAll()");
		
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);

		return ResponseEntity.ok(result);		
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Find ONE parking")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {		
		System.out.println("ParkingController findById()");
		
		Parking parking = parkingService.findById(id);		
		ParkingDTO result = parkingMapper.toParkingDTO(parking);

		return ResponseEntity.ok(result);		
	}
	
	@PostMapping
	@ApiOperation("Create one parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {		
		System.out.println("ParkingController create()");
		
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);

		return ResponseEntity.status(HttpStatus.CREATED).body(result);		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@ApiOperation("Remove ONE parking")
	public ResponseEntity delete(@PathVariable String id) {		
		System.out.println("ParkingController delete()");		
		parkingService.delete(id);				
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update one parking")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {		
		System.out.println("ParkingController update()");
		
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.update(id, parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);

		return ResponseEntity.status(HttpStatus.OK).body(result);		
	}
	
	@PutMapping("/{id}/exit")
	@ApiOperation("Exit from parking") 
	public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
	        Parking parking = parkingService.checkOut(id);
	        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
	    }
	

}
