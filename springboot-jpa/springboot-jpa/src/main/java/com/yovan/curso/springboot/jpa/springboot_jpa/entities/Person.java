package com.yovan.curso.springboot.jpa.springboot_jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String lastname;

  @Column(name = "programming_languaje")
  private String programmingLanguaje;

  @Embedded
  private Audit audit =new Audit();

  public Person() {
    
  }

  public Person(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
  }

  public Person(String name, String lastname, String programingLanguaje) {

    this.name = name;
    this.lastname = lastname;
    this.programmingLanguaje = programingLanguaje;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getProgrammingLanguaje() {
    return programmingLanguaje;
  }

  public void setProgrammingLanguaje(String programmingLanguaje) {
    this.programmingLanguaje = programmingLanguaje;
  }

  @Override
  public String toString() {
      return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
              + programmingLanguaje + ", createAt=" + audit.getCreatAt() + ", updated=" + audit.getUpdatedAt() + "]";
  }




}
