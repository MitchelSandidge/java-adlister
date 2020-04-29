package models;// INTERFACE FOR OUR CONTACT BEAN
    // (implemented by DAO)

import java.util.List;

public interface Contacts {

    // list all the contacts (List<models.Contact>)
    List<Contact> getContacts();

    // method for saving a contact
    // (get length of the List<models.Contact> and +1
    long saveContact(Contact contact);

    // method for deleting contacts
    void deleteContactById(long id);

    // method for getting a contact by their ID
    Contact getContactById(long id);
}
