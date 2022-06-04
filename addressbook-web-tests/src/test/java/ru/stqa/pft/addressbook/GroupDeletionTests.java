package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {

    gotoGroupPage();
    selectGroup();
    deleteSelectedGroup();
    returnToGroupPage();
  }

  private void deleteSelectedGroup() {
    wd.findElement(By.name("delete")).click();
  }

  private void selectGroup() {
    wd.findElement(By.xpath("//div[@id='content']/form/span[1]/input")).click();
  }


}
