package com.muharremtac.ab2015httptest.model;

import java.util.List;

/**
 * Created by mtac on 2/3/2015.
 */
public class Result {

    private String command;
    private List<Person> personList;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
