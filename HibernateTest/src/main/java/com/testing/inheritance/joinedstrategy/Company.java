package com.testing.inheritance.joinedstrategy;

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
@Table(name = "company")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  @OneToMany
  @JoinTable(name = "company_departament", joinColumns = @JoinColumn(name = "company_id"),
      inverseJoinColumns = @JoinColumn(name = "departament_id"))
  private Collection<Departament> departamentList = new ArrayList<Departament>();

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

  public Collection<Departament> getDepartamentList() {
    return departamentList;
  }

  public void setDepartamentList(Collection<Departament> departamentList) {
    this.departamentList = departamentList;
  }

  @Override
  public String toString() {
    return "Company [id=" + id + ", name=" + name + ", departamentList=" + departamentList + "]";
  }

}
