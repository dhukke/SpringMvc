package com.testing.inheritance.joinedstrategy;

import javax.persistence.Entity;

@Entity
public class Finance extends Departament {

  private int size;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "Finace [size=" + size + "]";
  }

}
