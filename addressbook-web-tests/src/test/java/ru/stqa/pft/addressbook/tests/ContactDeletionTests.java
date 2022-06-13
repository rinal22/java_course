package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Middle_name_test", "Last_name_test", "adress_test 123", "1234567890", "email@mail.ru"));
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().alertAccept();
  }



}
