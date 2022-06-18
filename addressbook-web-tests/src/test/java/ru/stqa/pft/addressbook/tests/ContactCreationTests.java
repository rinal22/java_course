package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


  @DataProvider

  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().
            withName("name1").
            withLastname("surname1").
            withAddress("adress1").
            withPhone("1634567891").
            withEmail("mail1@mail.ru")
    });
    list.add(new Object[]{new ContactData().
            withName("name2").
            withLastname("surname3").
            withAddress("address2").
            withPhone("1634567892").
            withEmail("mail2@mail.ru")
    });
    list.add(new Object[]{new ContactData().
            withName("name3").
            withLastname("surname3").
            withAddress("address3").
            withPhone("1634567893").
            withEmail("mail3@mail.ru")
    });
    return list.iterator();
  }
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().allCache();
    //File photo = new File("src/test/resources/vinni-pukh.png");
    //ContactData contact = new ContactData().withName("Name1").withLastname("Last_name_test").withPhoto(photo);

    app.contact().create(contact);
    app.goTo().homePage();
    Assert.assertEquals(app.contact().count(), before.size()+1);
    Contacts after = app.contact().allCache();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurretDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/vinni-pukh.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().allCache();
    ContactData contact = new ContactData().withName("Name1'").withLastname("Last_name_test");

    app.contact().create(contact);
    app.goTo().homePage();
    // Assert.assertEquals(after.size(), before.size());
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().allCache();

    assertThat(after, equalTo(before));
  }

}
