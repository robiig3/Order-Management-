package main.java.bll.validators.validators;

import main.java.model.Clients;

public class ClientAgeValidator implements Validator<Clients> {
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 70;

    public void validate(Clients t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }

    }

}
