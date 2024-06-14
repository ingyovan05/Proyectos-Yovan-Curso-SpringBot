package com.yovan.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yovan.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.yovan.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  // @Query("select p from Person p where p.id in ?1")
  @Query("select p from Person p where p.id not in ?1")
  List<Person> getPersonsByIds(List<Long> ids);

  @Query("select p.name , length(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p)")
  public List<Object[]> getNombresCortos();

  @Query("select p from Person p where p.id = (select max(p.id) from Person p) ")
  public Optional<Person> getLastRegistration();

  @Query("select 'sum --> ' || sum(p.id) || ' avg --> ' || avg(length(p.name)) || ' min --> ' || min(length(p.name)) || ' max --> ' || max(length(p.name))  from Person p")
  public String getMinMaxLengthName();

  @Query("select p.name , length(p.name) from Person p")
  public List<Object[]> getPersonNameLength();

  List<Person> findAllByOrderByNameAsc(); // Nativas

  List<Person> findAllByOrderByNameDesc(); // Nativas

  @Query("select p from Person p order by p.name asc")
  List<Person> getAllOrdered();

  // @Query ("select p.name from Person p where p.id between 2 and 5") Numeros
  @Query("select p.name from Person p where p.name between 'J' and 'Q' order by p.name , p.lastname desc") // Letras
  List<String> finAllBetweenId();

  @Query("select p.name from Person p where p.id between ?1 and ?2") // Numeros como parametros
  List<String> finAllBetweenId(Long id, Long id1);

  // @Query("select concat(p.name,' ',p.lastname) from Person p")
  // @Query("select p.name || ' ' || p.lastname from Person p")
  @Query("select upper (p.name || ' ' || p.lastname) ,' --> ',  lower (p.name || ' ' || p.lastname) from Person p")
  List<String> findAllFullNameConcat();

  @Query("select p.name from Person p")
  List<String> finAllNames();

  @Query("select distinct(p.name) from Person p")
  List<String> finAllNamesDistinct();

  @Query("select distinct(p.programmingLanguaje) from Person p")
  List<String> finAllprogrammingLanguajeDistinct();

  @Query("select count(distinct(p.programmingLanguaje)) from Person p")
  Long countAllprogrammingLanguajeDistinct();

  @Query("select 'total --> ' || count(p) || ' max --> ' || max(p.id) || ' min --> ' || min(p.id) from Person p")
  List<String> DevolverAgregacion();

  @Query("select new com.yovan.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name , p. lastname) from Person p")
  List<PersonDto> findAllPersonDto();

  @Query("select new Person(p.name , p. lastname) from Person p")
  List<Person> findAllPersonalizedPerson();

  @Query("select p.name from Person p where p.id=?1")
  String getNameById(Long id);

  @Query("select concat(p.name,' ',p.lastname) as fullname from Person p where p.id=?1")
  String getFullNameById(Long id);

  @Query("select p from Person p where p.id=?1")
  Optional<Person> findOne(Long id);

  @Query("select p from Person p where p.name=?1")
  Optional<Person> findOneName(String name);

  @Query("select p from Person p where p.name like %?1%")
  Optional<Person> findOneLikeName(String name);

  Optional<Person> findByNameContaining(String name);

  List<Person> findByProgrammingLanguaje(String programmingLanguaje);

  @Query("select p, p.programmingLanguaje from Person p")
  List<Object[]> findAllMixPersona();

  @Query("select p.name, p.programmingLanguaje from Person p")
  List<Object[]> obtenerPersonaData();

  @Query("select p.id,p.name,p.lastname, p.programmingLanguaje from Person p")
  List<Object[]> obtenerPersonaDataList();

  @Query("select p.id ,p.name ,p.lastname , p.programmingLanguaje from Person p where p.id= ?1")
  Optional<Object> obtenerPersonaDataById(Long id);

  @Query("select p.name, p.programmingLanguaje from Person p where p.programmingLanguaje=?1")
  List<Object[]> obtenerPersonaData(String programmingLanguaje);

  @Query("select p.name, p.programmingLanguaje from Person p where p.programmingLanguaje=?1 and p.name=?2")
  List<Object[]> obtenerPersonaData(String programmingLanguaje, String name);

}
