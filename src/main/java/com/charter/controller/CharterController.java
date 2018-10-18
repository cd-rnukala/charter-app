package com.charter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charter.component.CharterService;
import com.charter.dto.CalculateDistanceDto;
import com.charter.entity.Location;
import com.charter.entity.Plane;
import com.charter.utils.CharterCalulationUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/data")
@Slf4j
public class CharterController {
	@Autowired
	private CharterService charterService;

	/*
	 * 
	 * Requirement to fetch a plan by known id
	 */
	@RequestMapping("/plane/{id}")
	public ResponseEntity<Plane> getPlane(
			@PathVariable Integer id) {
		long startTime = System.currentTimeMillis();
		Plane p = charterService.getPlane(id);
		log.info("TimeTaken to respond for api-getPlane :{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<Plane>(p, HttpStatus.OK);
	}

	/*
	 * Requirement to calculate given the flight name, from & to locations
	 * 
	 */
	@RequestMapping("/plane/calculate")
	public ResponseEntity<CalculateDistanceDto> calculateDuration(
			@RequestBody CalculateDistanceDto calculateDistanceDto) {
		long startTime = System.currentTimeMillis();
		/*
		 * Get the given plane
		 */
		String planeName = calculateDistanceDto.getPlaneName();
		Plane planeObject = charterService.getPlane(planeName);

		/*
		 * from Location
		 */
		String fromLocation = calculateDistanceDto.getFromLocation();
		Location fromObject = Location.valueOf(fromLocation);

		/*
		 * toLocation
		 */
		String toLocation = calculateDistanceDto.getToLocation();
		Location toObject = Location.valueOf(toLocation);

		/*
		 * totalTimeTaken
		 */
		Double totalTimeTaken = CharterCalulationUtils.calculateDuration(planeObject, fromObject, toObject);
		calculateDistanceDto.setTotalTimeTaken(totalTimeTaken);
		log.info("TimeTaken to respond for calculateDuration:{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<CalculateDistanceDto>(calculateDistanceDto, HttpStatus.OK);
	}

	/*
	 * Requirement to fetch list of all the planes existing in the system
	 * 
	 */
	@RequestMapping("/planes")
	public ResponseEntity<List<Plane>> getPlanes() {
		long startTime = System.currentTimeMillis();
		List<Plane> planes = charterService.getPlanes();
		log.info("TimeTaken to respond for getPlanes:{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<List<Plane>>(planes, HttpStatus.OK);
	}

	/*
	 * Requirement to add a plane to the system
	 * 
	 */
	@RequestMapping(value = "/plane", method = RequestMethod.POST)
	public ResponseEntity<Plane> addPlane(@RequestBody Plane plane) {
		long startTime = System.currentTimeMillis();
		Plane p = charterService.addPlane(plane);
		log.info("TimeTaken to respond for addPlane:{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<Plane>(p, HttpStatus.OK);
	}

	/*
	 * Requirement to load list of planes to the system
	 * 
	 */
	@RequestMapping(value = "/planes", method = RequestMethod.POST)
	public ResponseEntity<List<Plane>> addPlanes(@RequestBody List<Plane> planes) {
		long startTime = System.currentTimeMillis();
		List<Plane> p = charterService.addPlanes(planes);
		log.info("TimeTaken to respond for addPlanes:{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<List<Plane>>(p, HttpStatus.OK);
	}

	/*
	 * Requirement to delete a plane from the system
	 * 
	 */
	@RequestMapping(value = "/plane", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePlane(
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
		long startTime = System.currentTimeMillis();
		Plane plane = charterService.getPlane(id);
		if (plane != null) {
			charterService.deletePlane(plane);
			return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		}
		log.info("TimeTaken to respond for deletePlane:{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
	}

	/*
	 * Requirement to update a particular plane
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/plane/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Plane> updatePlane(@RequestParam Long id, @RequestBody Plane plane) {
		long startTime = System.currentTimeMillis();
		plane = charterService.updatePlane(plane);

		if (null == plane) {
			return new ResponseEntity("No Plane found for ID " + id, HttpStatus.NOT_FOUND);
		}
		log.info("TimeTaken to respond for updatePlane:{} ms",(System.currentTimeMillis()-startTime));
		return new ResponseEntity<Plane>(plane, HttpStatus.OK);
	}
}