package com.muharremtac.ab2015httptest;

import com.google.gson.Gson;
import com.muharremtac.ab2015httptest.model.Person;
import com.muharremtac.ab2015httptest.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtac on 2/3/2015.
 */
public class TestCase {

    public static void main(String[] args){
        Gson gson = new Gson();
        Result r = new Result();
        List<Person> personList = new ArrayList<Person>();
        Person p = new Person();
        p.setIsim("Muharrem");
        p.setSoyIsim("Tac");
        personList.add(p);
        p = new Person();
        p.setIsim("Mustafa");
        p.setSoyIsim("Ozcan");
        personList.add(p);
        r.setPersonList(personList);
        String json= gson.toJson(r);

    }

}
