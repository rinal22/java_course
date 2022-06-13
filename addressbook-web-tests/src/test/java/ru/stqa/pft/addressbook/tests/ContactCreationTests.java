package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withName("Name1").withLastname("Last_name_test");

    app.contact().create(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);

    contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }

}
