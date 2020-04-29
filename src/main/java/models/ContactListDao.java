package models;

import java.util.ArrayList;
import java.util.List;

public class ContactListDao implements Contacts {


    // We need a List variable to hold all the models.Contacts
    private List<Contact> contacts = new ArrayList<>();



//    contacts.add(new models.Contact("dlks", "Smith", "1212323"));


    // list all the contacts (List<models.Contact>)
    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    // method for saving a contact
    // (get length of the List<models.Contact> and +1
    @Override
    public long saveContact(Contact contact) {
        // check to see if this is the first contact, and if so, add 1
        if(contact.getId() == 0) {
            contact.setId(contacts.size() + 1);
            contacts.add(contact);
        } else {
            // we already have the correct ID coming from the MySQL table
            contacts.set((int) (contact.getId() - 1), contact);
        }
        // return the ID of the newly saved contact
        return contact.getId();
    }

    // method for deleting contacts
    @Override
    public void deleteContactById(long id) {
        // do a .remove on the passed in Id to remove that contact
        contacts.remove((int) id -1);
    }

    // method for getting a contact by their ID
    @Override
    public Contact getContactById(long id) {
        return contacts.get((int) id -1);
    }

    // test main

    public static void main(String[] args) {
        Contacts contactDao = new ContactListDao();

        List<Contact> allContacts = contactDao.getContacts();

        for (Contact contact : allContacts) {
            System.out.println(contact.getFirstName());
        }
    }
}
