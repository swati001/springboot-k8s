package com.boot.camel.demo.dao;

public interface PersonDAO {

    Collection<Person> findAll();

    Person getPersonById(Long id);

    void removePerson(Long id);

    Person save(final Person person);

    void save(final List<Person> people);

    Person update(final Person person);

}