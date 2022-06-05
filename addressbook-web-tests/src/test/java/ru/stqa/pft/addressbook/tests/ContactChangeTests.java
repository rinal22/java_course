package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactChangeTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Name11", "Middle_name_test11", "Last_name_test11", "11adress_test 123", "111234567890","1"));
    app.getContactHelper().editSubmit();
    app.getNavigationHelper().goToHomePage();
  }

}
