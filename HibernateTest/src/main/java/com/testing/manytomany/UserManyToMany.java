package com.testing.manytomany;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "many_to_many_user")
public class UserManyToMany {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  private String userName;

  @ManyToMany
  private Collection<VehicleManyToMany> vehicle = new ArrayList<VehicleManyToMany>();

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

  public Collection<VehicleManyToMany> getVehicle() {
    return vehicle;
  }

  public void setVehicle(Collection<VehicleManyToMany> vehicle) {
    this.vehicle = vehicle;
  }

  @Override
  public String toString() {
    return "UserManyToMany [userId=" + userId + ", userName=" + userName + ", vehicle=" + vehicle
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
    UserManyToMany other = (UserManyToMany) obj;
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
