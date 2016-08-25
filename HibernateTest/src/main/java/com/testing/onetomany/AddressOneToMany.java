package com.testing.onetomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity(name = "address_one_to_many")
public class AddressOneToMany {

  @Id
  @GeneratedValue
  private int addressId;
  private String addressName;
  
  @ManyToOne
  @JoinColumn(name="USER_ID")
  private UserOneToMany userOneToMany;

  public UserOneToMany getUserOneToMany() {
    return userOneToMany;
  }

  public void setUserOneToMany(UserOneToMany userOneToMany) {
    this.userOneToMany = userOneToMany;
  }

  public int getAddressId() {
    return addressId;
  }

  public void setAddressId(int addressId) {
    this.addressId = addressId;
  }

  public String getAddressName() {
    return addressName;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }

  @Override
  public String toString() {
    return "AddressOneToMany [addressId=" + addressId + ", addressName=" + addressName
        + ", userOneToMany=" + userOneToMany + "]";
  }

}
