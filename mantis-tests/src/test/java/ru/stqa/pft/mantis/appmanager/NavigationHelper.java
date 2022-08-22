package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void loginPage() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
  }

  public void managePage() {
    click(By.xpath("//div[@id='sidebar']/ul/li[7]/a/i"));
    click(By.xpath("//ul[@class='nav nav-tabs padding-18']/li[2]"));

  }

  public void usersManageTab() {
    click(By.linkText("Manage Users"));




  }
}
