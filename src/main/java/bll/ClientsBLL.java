package main.java.bll;

import main.java.bll.validators.validators.ClientAgeValidator;
import main.java.bll.validators.validators.EmailValidator;
import main.java.bll.validators.validators.Validator;
import main.java.dao.ClientsDAO;
import main.java.model.Clients;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientsBLL {

    private List<Validator<Clients>> validators;

    public ClientsBLL() {
        validators = new ArrayList<Validator<Clients>>();
		validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());
    }

    public Clients findByName(String name) {
        Clients st = ClientsDAO.findByName(name);
        if (st == null) {
            throw new NoSuchElementException("The client with name =" + name + " was not found!");
        }
        return st;
    }

    public String insertClient(Clients client) {
        for (Validator<Clients> v : validators) {
            v.validate(client);
        }
        return ClientsDAO.insert(client);
    }
}
