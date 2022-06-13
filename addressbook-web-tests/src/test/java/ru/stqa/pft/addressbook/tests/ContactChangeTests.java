package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.HashSet;
import java.util.Set;

public class ContactChangeTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withName("Name1").withLastname("Last_name_test"));
    }
  }
  @Test
  public void testContactDeletion() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Name11").withMiddlename("Middle_name_test11").withLastname("Last_name_test11").withAddress("11adress_test 123").withPhone("111234567890");

    app.contact().modify(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);

    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }



}
