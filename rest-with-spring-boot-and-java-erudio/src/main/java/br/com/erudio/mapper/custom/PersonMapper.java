package br.com.erudio.mapper.custom;

import br.com.erudio.data.vo.v2.PersonVOv2;
import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOv2 convertEntityToVo(Person person){
        PersonVOv2 vo = new PersonVOv2();
        vo.setId(person.getId());
        vo.setBirthDate(new Date());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        return vo;
    }
    public Person convertVoToEntity (PersonVOv2 vo){
        Person person = new Person();
        person.setId(vo.getId());
        person.setAddress(vo.getAddress());
        person.setGender(vo.getGender());
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getLastName());
        return person;
    }
}
