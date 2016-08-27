package com.testing.inheritance.defaultway;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "owner_pet", joinColumns = @JoinColumn(name = "owner_id"),
      inverseJoinColumns = @JoinColumn(name = "pet_id"))
  private Collection<Pet> petList = new ArrayList<Pet>();

  public int getId() {
    return id;
  }

  public void setId(int movieId) {
    this.id = movieId;
  }

  public String getName() {
    return name;
  }

  public void setName(String movieName) {
    this.name = movieName;
  }

  public Collection<Pet> getPetList() {
    return petList;
  }

  public void setPetList(Collection<Pet> petList) {
    this.petList = petList;
  }

  @Override
  public String toString() {
    return "Owner [id=" + id + ", name=" + name + ", petList=" + petList + "]";
  }

}
