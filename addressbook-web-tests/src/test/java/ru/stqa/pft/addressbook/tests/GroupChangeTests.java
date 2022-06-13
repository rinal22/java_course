package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupChangeTests extends TestBase{
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new GroupData(null,"test1", "test2", "test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().editGroup();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"test1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().editGroupSubmit();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
