package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().allCache();
    File photo = new File("src/test/resources/vinni-pukh.png");
    ContactData contact = new ContactData().withName("Name1").withLastname("Last_name_test").withPhoto(photo);

    app.contact().create(contact);
    app.goTo().homePage();
    Assert.assertEquals(app.contact().count(), before.size()+1);
    Contacts after = app.contact().allCache();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = true)
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
