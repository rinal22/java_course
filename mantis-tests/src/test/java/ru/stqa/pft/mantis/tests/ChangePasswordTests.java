package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer (){
    app.mail().start();
  }

  @Test
  public void changePasswordTest () throws IOException, MessagingException {
    UsersData user = app.db().usersWithoutAdmin().iterator().next();
    String userName = user.getUserName();
    String email = user.getEmail();
    String newPassword = "password";
    app.session().login((String) app.getProperty("web.adminLogin"), (String) app.getProperty("web.adminPassword"));
    app.goTo().managePage();
    app.goTo().usersManageTab();
    app.user().selectUser(user.getId());
    app.user().passwordReset();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    long now = System.currentTimeMillis();
    String realname = String.format("realname%s", now);
    app.registration().finishChangingPassword(realname, confirmationLink, newPassword);
    assertTrue(app.newSession().login(userName, newPassword));
  }
  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }


}
