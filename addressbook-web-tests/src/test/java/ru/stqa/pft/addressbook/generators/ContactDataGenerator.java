package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static List<ContactData> generateContacts(int count) {

    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().
              withName(String.format("name %s", i)).
              withLastname(String.format("surname %s", i)).
              withAddress(String.format("address %s", i)).
              withPhone(String.format("00000 %s", i)).
              withEmail(String.format("mail%s@mail.ru", i))

      );
    }
    return contacts;
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);

    for (ContactData contact : contacts) {
      writer.write(String.format(
              "%s;%s;%s;%s;%s\n",

              contact.getName(),
              contact.getLastname(),
              contact.getAddress(),
              contact.getPhone(),
              contact.getEmail()

      ));
    }
    writer.close();
  }


}