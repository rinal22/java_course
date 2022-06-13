package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactChangeTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Middle_name_test", "Last_name_test", "adress_test 123", "1234567890", "email@mail.ru"));
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Name11", "Middle_name_test11", "Last_name_test11", "11adress_test 123", "111234567890","1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().editSubmit();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

}
