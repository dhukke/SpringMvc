package com.testing.manytomany;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "many_to_many_vehicle")
public class VehicleManyToMany {

  @Id
  @GeneratedValue
  private int vehicleId;
  private String vehicleName;

  @ManyToMany(mappedBy="vehicle")
  private Collection<UserManyToMany> userOneToMany = new ArrayList<UserManyToMany>();

  public Collection<UserManyToMany> getUserOneToMany() {
    return userOneToMany;
  }

  public void setUserOneToMany(Collection<UserManyToMany> userOneToMany) {
    this.userOneToMany = userOneToMany;
  }

  public int getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getVehicleName() {
    return vehicleName;
  }

  public void setVehicleName(String vehicleName) {
    this.vehicleName = vehicleName;
  }

  @Override
  public String toString() {
    return "VehicleManyToMany [vehicleId=" + vehicleId + ", vehicleName=" + vehicleName
        + ", userOneToMany=" + userOneToMany + "]";
  }

}
