package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "ContactCount")
  public int count;
  @Parameter(names = "-f", description = "TargetFile")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);

    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);

    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {

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

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
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
    }


  }
}