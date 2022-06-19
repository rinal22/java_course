package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestBase {
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test" + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test" + m.getName());
  }

  public ApplicationManager getApp() {
    return app;
  }

  public void verifyGroupListInUi() {

//Добавить в конфигурацию в VM options -DverifyUI=true
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(
//dbGroups упрощена до групп без хедера и футера
              dbGroups.stream().map((g) -> new GroupData().
                      withId(g.getId()).
                      withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUi() {

//Добавить в конфигурацию в VM options -DverifyUI=true
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().allCache();

      MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(
              dbContacts.stream().map((g) -> new ContactData().
                              withId(g.getId()).
                      withName(g.getName()).
                      withLastname(g.getLastname()).
                      withAddress(g.getAddress()).
                      withPhone(g.getPhone()).
                      withEmail(g.getEmail())

              ).collect(Collectors.toSet())
      ));
    }
  }
}
