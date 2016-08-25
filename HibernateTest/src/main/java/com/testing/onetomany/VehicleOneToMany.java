package com.testing.onetomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "vehicle_one_to_many")
public class VehicleOneToMany {

  @Id
  @GeneratedValue
  private int vehicleId;
  private String vehicleName;
  @ManyToOne
  private UserOneToMany userOneToMany;

  public UserOneToMany getUserOneToMany() {
    return userOneToMany;
  }

  public void setUserOneToMany(UserOneToMany userOneToMany) {
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
    return "VehicleOneToMany [vehicleId=" + vehicleId + ", vehicleName=" + vehicleName
        + ", userOneToMany=" + userOneToMany + "]";
  }

}
