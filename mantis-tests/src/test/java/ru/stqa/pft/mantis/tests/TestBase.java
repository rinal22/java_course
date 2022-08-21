package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    app.ftp().upload(new File("C:/xampp/htdocs/mantisbt-2.25.4/config/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  public boolean isIssueOpen (int issueId) throws MalformedURLException, ServiceException, RemoteException {
    IssueData issue = app.soap().getIssue(issueId);
    if (issue.getStatus().getName().equals("resolved") || issue.getStatus().getName().equals("closed")) {
      return false;
    }
    return true;
  }
  public void skipIfNotFixed (int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      System.out.println("ignored because of issue " + issueId);
    }
  }
  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }


}