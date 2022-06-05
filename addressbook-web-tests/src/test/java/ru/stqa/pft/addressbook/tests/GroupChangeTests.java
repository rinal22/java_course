package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupChangeTests extends TestBase{
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("1test1", "2test2", "3test3"));
    app.getGroupHelper().editGroupSubmit();
    app.getGroupHelper().returnToGroupPage();
  }
}
