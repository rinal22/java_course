package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {
  private int id;
  private String subject;
  private String description;
  private String state_name;


  public int getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public String getDescription() {
    return description;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getState_name() {
    return state_name;
  }

  public Issue withState_name(String state_name) {
    this.state_name = state_name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Issue issue = (Issue) o;

    if (id != issue.id) return false;
    if (!Objects.equals(subject, issue.subject)) return false;
    return Objects.equals(description, issue.description);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (subject != null ? subject.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }
}
