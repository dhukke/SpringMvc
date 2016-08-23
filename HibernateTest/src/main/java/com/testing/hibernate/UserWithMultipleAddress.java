package com.testing.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_test_2")
public class UserWithMultipleAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  private String userName;

  @ElementCollection
  @JoinTable(name = "user_test_2_address", joinColumns = @JoinColumn(name = "USER_ID"))
  @GenericGenerator(name = "sequence", strategy = "sequence")
  @CollectionId(columns = {@Column(name = "ADDRESS_ID")}, generator = "sequence",
      type = @Type(type = "long"))
  private Collection<Address> addresses = new ArrayList<Address>();

  public void addAddress(Address address) {
    addresses.add(address);
  }

  public Collection<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Collection<Address> addresses) {
    this.addresses = addresses;
  }

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

  @Override
  public String toString() {
    return "UserWithMultipleAddress [userId=" + userId + ", userName=" + userName + ", addresses="
        + addresses + "]";
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
    UserWithMultipleAddress other = (UserWithMultipleAddress) obj;
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
