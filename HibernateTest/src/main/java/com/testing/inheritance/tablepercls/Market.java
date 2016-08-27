package com.testing.inheritance.tablepercls;

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
@Table(name = "market")
public class Market {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  @OneToMany
  @JoinTable(name = "market_product", joinColumns = @JoinColumn(name = "market_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Collection<Product> productList = new ArrayList<Product>();

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

  public Collection<Product> getProductList() {
    return productList;
  }

  public void setProductList(Collection<Product> productList) {
    this.productList = productList;
  }

  @Override
  public String toString() {
    return "Market [id=" + id + ", name=" + name + ", productList=" + productList + "]";
  }

}
