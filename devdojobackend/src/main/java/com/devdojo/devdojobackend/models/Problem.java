package com.devdojo.devdojobackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "problems")
public class Problem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long pid;
  private String title;
  private String acceptance;
  private String difficulty;
  private String frequency;
  private String devDojoLink;

  public void setID(Long id) {
    this.id = id;
  }

  public Long getID() {
    return this.id;
  }

  public void setPID(Long pid) {
    this.pid = pid;
  }

  public Long getPID() {
    return this.pid;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setAcceptance(String acceptance) {
    this.acceptance = acceptance;
  }

  public String getAcceptance() {
    return this.acceptance;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public String getDifficulty() {
    return this.difficulty;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public String getFrequency() {
    return this.frequency;
  }

  public void setDevDojoLink(String devDojoLink) {
    this.devDojoLink = devDojoLink;
  }

  public String getDevDojoLink() {
    return this.devDojoLink;
  }

  public Problem() {

  }

  public Problem(Long id, Long pid, String title, String acceptance, String difficulty, String frequency,
      String devDojoLink) {
    this.id = id;
    this.pid = pid;
    this.title = title;
    this.acceptance = acceptance;
    this.difficulty = difficulty;
    this.frequency = frequency;
    this.devDojoLink = devDojoLink;
  }

}