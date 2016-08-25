package com.testing.onetomany;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_one_to_many")
public class UserOneToMany {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  private String userName;

  @OneToMany
  @JoinTable(name = "one_to_many_user_vehicle", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
  private Collection<VehicleOneToMany> vehicle = new ArrayList<VehicleOneToMany>();

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Collection<VehicleOneToMany> getVehicle() {
    return vehicle;
  }

  public void setVehicle(Collection<VehicleOneToMany> vehicle) {
    this.vehicle = vehicle;
  }

  @Override
  public String toString() {
    return "UserOneToMany [userId=" + userId + ", userName=" + userName + ", vehicle=" + vehicle
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + userId;
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserOneToMany other = (UserOneToMany) obj;
    if (userId != other.userId)
      return false;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    return true;
  }

}
