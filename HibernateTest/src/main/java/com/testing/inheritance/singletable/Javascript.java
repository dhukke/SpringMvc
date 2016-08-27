package com.testing.inheritance.singletable;

import javax.persistence.Entity;

@Entity
public class Javascript extends Language {

  private String javascriptProperty;

  public String getJavascriptProperty() {
    return javascriptProperty;
  }

  public void setJavascriptProperty(String javascriptProperty) {
    this.javascriptProperty = javascriptProperty;
  }

  @Override
  public String toString() {
    return "Javascript [javascriptProperty=" + javascriptProperty + "]";
  }

}
