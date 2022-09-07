package br.com.erudio.controllers;

import br.com.erudio.data.vo.v1.PersonVO;
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
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }

    @GetMapping()
    public List<PersonVO> findAl() {
        return services.findAll();
    }

    @PostMapping()
    public PersonVO save(@RequestBody PersonVO person) {
        return services.save(person);
    }

    @PutMapping()
    public PersonVO update(@RequestBody PersonVO person) {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
