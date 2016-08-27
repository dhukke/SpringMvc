package com.testing.inheritance.joinedstrategy;

import javax.persistence.Entity;

@Entity
public class Hr extends Departament {

  private int people;

  public int getPeople() {
    return people;
  }

  public void setPeople(int people) {
    this.people = people;
  }

  @Override
  public String toString() {
    return "Hr [people=" + people + "]";
  }


}
