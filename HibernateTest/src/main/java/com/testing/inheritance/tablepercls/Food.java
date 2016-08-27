package com.testing.inheritance.tablepercls;

import javax.persistence.Entity;

@Entity
public class Food extends Product {

  private int type;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Food [type=" + type + "]";
  }

}
