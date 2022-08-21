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
    click(By.xpath("//a[@href='/mantisbt-2.25.4/manage_overview_page.php']"));
    click(By.xpath("//a[@href='/mantisbt-2.25.4/manage_user_page.php']"));

  }

  public void usersManageTab() {
    click(By.linkText("Manage Users"));




  }
}
