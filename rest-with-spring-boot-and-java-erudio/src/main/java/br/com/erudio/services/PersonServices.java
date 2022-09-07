package br.com.erudio.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOv2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
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
    @Autowired
    private PersonMapper mapper;


    public PersonVO findById(Long id){
        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all person!");
        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }

    public PersonVO save(PersonVO person){
        logger.info("Save one person!");
        var entity = repository.save(DozerMapper.parseObject(person, Person.class));
        return DozerMapper.parseObject(entity,PersonVO.class);
    }
    public PersonVOv2 save(PersonVOv2 person){
        logger.info("Save one person!");
        var entity = repository.save(mapper.convertVoToEntity(person));
        return mapper.convertEntityToVo(entity);
    }


    public PersonVO update(PersonVO person){
        logger.info("Update one person!");
        findById(person.getId());
        var entity = repository.save(DozerMapper.parseObject(person, Person.class));
        return DozerMapper.parseObject(entity,PersonVO.class);
    }

    public void delete(Long id){
        logger.info("Delete one person!");
        PersonVO person = findById(id);
        repository.delete(DozerMapper.parseObject(person, Person.class));
    }

}
