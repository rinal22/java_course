package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withName("Contact").
              withLastname("with phone").withPhone("111").withMobilePhone("222").withWorkPhone("333"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData  contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.getPhone(), equalTo(cleaned(contactInfoFromEditForm.getPhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
