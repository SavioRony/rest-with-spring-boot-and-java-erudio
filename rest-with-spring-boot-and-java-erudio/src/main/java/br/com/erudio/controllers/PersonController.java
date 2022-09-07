package br.com.erudio.controllers;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;
    @GetMapping("/{id}")
    public Person findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }

    @GetMapping()
    public List<Person> findAl() {
        return services.findAll();
    }

    @PostMapping()
    public Person save(@RequestBody Person person) {
        return services.save(person);
    }

    @PutMapping()
    public Person update(@RequestBody Person person) {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
