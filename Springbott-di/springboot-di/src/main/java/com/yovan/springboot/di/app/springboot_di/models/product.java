package com.yovan.springboot.di.app.springboot_di.models;

public class product implements Cloneable {

  private Long id;
  private String name;
  private Long price;

  public product() {

  }

  public product(final Long id, final String name, final Long price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(final Long price) {
    this.price = price;
  }

  @Override
  public Object clone() {
        try{
      return super.clone();
    } catch (CloneNotSupportedException e){
      return new product(getId(),getName(),getPrice() );
    }
  }
}