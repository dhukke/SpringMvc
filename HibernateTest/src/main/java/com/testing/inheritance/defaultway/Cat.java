package com.testing.inheritance.defaultway;

import javax.persistence.Entity;

@Entity
public class Cat extends Pet {

  private String personality;

  public String getPersonality() {
    return personality;
  }

  public void setPersonality(String personality) {
    this.personality = personality;
  }

  @Override
  public String toString() {
    return "Dog [personality=" + personality + "]";
  }

}
