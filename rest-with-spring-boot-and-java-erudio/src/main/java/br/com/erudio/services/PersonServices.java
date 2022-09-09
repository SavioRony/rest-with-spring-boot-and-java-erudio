package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
       PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
       vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
       vo.add(linkTo(methodOn(PersonController.class).update(vo)).withRel("update").withType("PUT"));
       return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all person!");
        List<PersonVO> voList = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        voList.stream().forEach( p -> {
            p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withRel("Get_by_ID").withType("GET"));
            p.add(linkTo(methodOn(PersonController.class).update(p)).withRel("update").withType("PUT"));
        });

        return voList;
    }

    public PersonVO save(PersonVO person) {
        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("Save one person!");
        var entity = repository.save(DozerMapper.parseObject(person, Person.class));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withRel("Get_by_ID").withType("GET"));
        vo.add(linkTo(methodOn(PersonController.class).update(vo)).withRel("update").withType("PUT"));
        return vo;
    }

    public PersonVO update(PersonVO person) {
        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("Update one person!");
        findById(person.getKey());
        var entity = repository.save(DozerMapper.parseObject(person, Person.class));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withRel("Get_by_ID").withType("GET"));
        return vo;
    }

    public void delete(Long id) {
        logger.info("Delete one person!");
        PersonVO person = findById(id);
        repository.delete(DozerMapper.parseObject(person, Person.class));
    }

}
