package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import java.io.File;
import javax.persistence.*;

@Entity
@Table(name="addressbook")
@XStreamAlias("Contact")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Column(name="firstname")
  private String name;
  @Column(name="middlename")
  private String middlename;
  @Column(name="lastname")
  private String lastname;
  @Column(name="address")
  @Type(type = "text")
  private String address;
  @Column(name="home")
  @Type(type = "text")
  private String phone;
  @Column(name="mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Column(name="work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;
  @Type(type = "text")
  private String email;
  @Type(type = "text")
  private String email2;
  @Type(type = "text")
  private String email3;
  @Transient
  private String allEmails;

  @Column(name="photo")
  @Type(type = "text")
  private String photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }


  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }



  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }
  public String getMobilePhone() {return mobilePhone;}

  public String getWorkPhone() {return workPhone;}


  public String getAllPhones() {return allPhones;}

  public String getEmail() {return email;}

  public String getEmail2() {return email2;}

  public String getEmail3() {return email3;}

  public String getAllEmails() {return allEmails;}

  public File getPhoto() {return new File(photo);}

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}






