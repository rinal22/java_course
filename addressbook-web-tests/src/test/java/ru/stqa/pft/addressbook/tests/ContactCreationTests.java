package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Name1", "Middle_name_test", "Last_name_test", "adress_test 123", "1234567890", "email@mail.ru", "test1"),true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();

  }

}
