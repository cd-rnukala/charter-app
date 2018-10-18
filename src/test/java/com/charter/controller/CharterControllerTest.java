package com.charter.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.charter.component.CharterService;
import com.charter.entity.Plane;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CharterControllerTest {

	@InjectMocks
	private CharterController charterController;

	@Mock
	private CharterService charterService;

	@Test
	public void testGetPlanes() {
		List<Plane> planes = new ArrayList<Plane>();
		planes.add(new Plane(101, "AirPartner", 300.00, 100.00));
		planes.add(new Plane(201, "MillionAir", 600.00, 150.00));
		planes.add(new Plane(301, "Belavia", 1000.00, 250.00));

		Mockito.when(charterService.getPlanes()).thenReturn(planes);
		List<Plane> fetchedPlanes = (List<Plane>) charterController.getPlanes().getBody();
		assertThat(planes).hasSize(fetchedPlanes.size());
	}

}
