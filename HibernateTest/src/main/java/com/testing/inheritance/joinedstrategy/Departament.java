package com.testing.inheritance.joinedstrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "departament")
@Inheritance(strategy = InheritanceType.JOINED)
public class Departament {

  @Id
  @GeneratedValue
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Departament [id=" + id + ", name=" + name + "]";
  }

}
