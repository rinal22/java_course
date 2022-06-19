package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().

              withName("test66").
              withLastname("lastname66").
              withAddress("address66").
              withPhone("666666").
              withEmail("mail66@mail.ru")

              .inGroup(groups.iterator().next())
      );
    }

  }

  @Test
  public void testContactRemoveGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    int i = contact.getId();

    if(contact.getGroups().size() == 0){

      GroupData new_group = app.db().groups().iterator().next();
      app.contact().contactInGroup(contact,new_group);
      app.goTo().homePage();
    }

    ContactData new_contact = app.db().getContactById(i);

    Groups groupDelete = new_contact.getGroups();

    app.contact().contactRemoveGroup(new_contact);

    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(groupDelete), equalTo(false));
  }

}
