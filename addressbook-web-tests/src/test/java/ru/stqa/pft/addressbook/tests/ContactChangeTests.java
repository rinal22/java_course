package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactChangeTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Middle_name_test", "Last_name_test", "adress_test 123", "1234567890", "email@mail.ru"));
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Name11", "Middle_name_test11", "Last_name_test11", "11adress_test 123", "111234567890","1"), false);
    app.getContactHelper().editSubmit();
    app.getNavigationHelper().goToHomePage();
  }

}
