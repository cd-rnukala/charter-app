package com.charter.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.charter.entity.Plane;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CharterServiceImpl implements CharterService {
	@Override
	public Plane getPlane(Integer id) {
		return planes.stream().filter(plan -> plan.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public Plane getPlane(String name) {
		return planes.stream().filter(plan -> plan.getName().equals(name)).findFirst().orElse(null);
	}
	
	private List<Plane> planes;

	@PostConstruct
	public void initPlanes()
	{
		planes = new ArrayList<Plane>();
		planes.add(new Plane(101, "AirPartner", 300.00, 100.00));
		planes.add(new Plane(201, "MillionAir", 600.00, 150.00));
		planes.add(new Plane(301, "Belavia", 1000.00, 250.00));
	}
	
	@Override
	public List<Plane> getPlanes() {
		return planes;
	}

	@Override
	public Plane addPlane(Plane plane) {
		planes.add(plane);
		return plane;
	}

	@Override
	public void deletePlane(Plane plane) {
		Iterator<Plane> planesItr = planes.iterator();
		while (planesItr.hasNext()) {
			Plane fetchedPlane = planesItr.next();
			if (fetchedPlane.getId().equals(plane.getId())) {
				log.info("Found the plan object passed in:{}", plane);
				planesItr.remove();
			}
		}
		return;
	}

	@Override
	public Plane updatePlane(Plane plane) {
		Iterator<Plane> planesItr = planes.iterator();
		while (planesItr.hasNext()) {
			Plane fetchedPlane = planesItr.next();
			if (fetchedPlane.getId().equals(plane.getId())) {
				log.info("Found the plan object passed in:{}", plane);
				planesItr.remove();
				planes.add(plane);
			}
		}
		return plane;
	}

	@Override
	public List<Plane> addPlanes(List<Plane> addPlanes) {
		addPlanes.parallelStream().forEach(plane -> addPlane(plane));
		return planes;
	}
}
