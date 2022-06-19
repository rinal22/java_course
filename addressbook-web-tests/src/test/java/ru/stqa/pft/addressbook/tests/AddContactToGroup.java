package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroup extends TestBase {
  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().
              withName("test33")
      );
    }
    if (app.contact().all().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withName("test44").
              withLastname("lastname44").
              withAddress("address44").
              withPhone("444444").
              withEmail("mail44@mail.ru")
      );
    }

    app.contact().showContactsWithoutGroup();
    Set<ContactData> contactsWithoutGroup = app.contact().all();
    if (contactsWithoutGroup.size() == 0) {
      app.contact().create(new ContactData().
              withName("test55").
              withLastname("lastname55").
              withAddress("address55").
              withPhone("555555").
              withEmail("mail55@mail.ru")
      );
    }
  }


  @Test
  public void testAddInGroupContact() {

    app.contact().showContactsWithoutGroup();
    Set<ContactData> contactsWithoutGroup = app.contact().all();

    ContactData contactNoneGroup = contactsWithoutGroup.iterator().next();

    int id = contactNoneGroup.getId();

    ContactData contact = app.db().getContactById(id);

    GroupData group = app.db().groups().iterator().next();
    app.contact().contactInGroup(contact, group);

    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(group), equalTo(true));

    verifyContactListInUi();
  }
}