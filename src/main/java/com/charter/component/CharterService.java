package com.charter.component;

import java.util.List;

import com.charter.entity.Plane;

public interface CharterService {
  public Plane getPlane(Integer id);
  
  public Plane getPlane(String name);
  
  public List<Plane> getPlanes();
  
  public Plane addPlane(Plane plane);
  
  public List<Plane> addPlanes(List<Plane> plane);
  
  public void deletePlane(Plane plane);
  
  public Plane updatePlane(Plane plane);
}
