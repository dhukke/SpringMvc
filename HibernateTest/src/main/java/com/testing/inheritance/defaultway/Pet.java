package com.testing.inheritance.defaultway;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "pet")
public class Pet {

  @Id
  @GeneratedValue
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int genreId) {
    this.id = genreId;
  }

  public String getName() {
    return name;
  }

  public void setName(String genreName) {
    this.name = genreName;
  }

  @Override
  public String toString() {
    return "Pet [id=" + id + ", name=" + name + "]";
  }

}
