package com.testing.inheritance.singletable;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app")
public class App {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  @OneToMany
  @JoinTable(name = "app_language", joinColumns = @JoinColumn(name = "app_id"),
      inverseJoinColumns = @JoinColumn(name = "language_id"))
  private Collection<Language> languageList = new ArrayList<Language>();

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

  public Collection<Language> getLanguageList() {
    return languageList;
  }

  public void setLanguageList(Collection<Language> languageList) {
    this.languageList = languageList;
  }

  @Override
  public String toString() {
    return "App [id=" + id + ", name=" + name + ", languageList=" + languageList + "]";
  }
  
}
