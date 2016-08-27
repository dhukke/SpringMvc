package com.testing.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Java_Language")
public class Java extends Language {

  private String javaProperty;

  public String getJavaProperty() {
    return javaProperty;
  }

  public void setJavaProperty(String javaProperty) {
    this.javaProperty = javaProperty;
  }

  @Override
  public String toString() {
    return "Java [javaProperty=" + javaProperty + "]";
  }

}
