package com.testing.inheritance.tablepercls;

import javax.persistence.Entity;

@Entity
public class Cloth extends Product {

  private int size;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "Cloth [size=" + size + "]";
  }

}
