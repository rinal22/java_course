package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneEmailTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withName("Contact").
              withLastname("with phone").withPhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("111@1.ru").withEmail2("222@2.ru").withEmail3("333@3.ru"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData  contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    //assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    //assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhone(),contact.getMobilePhone(),contact.getWorkPhone()).
            stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneEmailTests::cleanedPhone)
            .collect(Collectors.joining("\n"));
  }
  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).
            stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneEmailTests::cleanedEmail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhone(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

  public static String cleanedEmail(String phone){
    return phone.replaceAll("\\s","");
  }
}
