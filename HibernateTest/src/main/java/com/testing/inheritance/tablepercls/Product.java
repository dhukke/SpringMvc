package com.testing.inheritance.tablepercls;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {

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
    return "Product [id=" + id + ", name=" + name + "]";
  }


}
