package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.name());
    type(By.name("middlename"),contactData.middlename());
    type(By.name("lastname"),contactData.lastname());
    type(By.name("address"),contactData.address());
    type(By.name("home"),contactData.phone());
    type(By.name("email"),contactData.email());
  }

  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void editSubmit() {
    click(By.name("update"));
  }
}
