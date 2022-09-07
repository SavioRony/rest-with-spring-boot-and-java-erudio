package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding one person!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public List<Person> findAll(){
        logger.info("Finding all person!");
        return repository.findAll();
    }

    public Person save(Person person){
        logger.info("Save one person!");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Update one person!");
        findById(person.getId());
        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Delete one person!");
        Person person = findById(id);
        repository.delete(person);
    }

}
