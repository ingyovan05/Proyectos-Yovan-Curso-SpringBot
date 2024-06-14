package com.yovan.curso.springboot.jpa.springboot_jpa;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.yovan.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.yovan.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.yovan.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {
	@Autowired

	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create();
		// delete2();
		update();

	}



	@Transactional(readOnly = true)
	public void DevolverNombreCorto(){
		// System.out.println("=====Consulta Nombre corto =======");

		// List<Object[]> personreg = repository.getNombresCortos();
		// personreg.forEach(reg -> {
		// 	System.out.println("Longitud nombre= " + reg[1] + ", nombre=" + reg[0]);
		// });

		// System.out.println("=====Ultimo registro =======");
		// Optional<Person> optionalPerson = repository.getLastRegistration();
		// optionalPerson.ifPresent(person -> {
		// 	System.out.println(person);	
		// });

		System.out.println("=====Where in =======");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1l,2L,5L));
		persons.forEach(System.out::println);

		

	}

	@Transactional(readOnly = true)
public void DevolverMinMax(){
	System.out.println("=====Consulta Min y Max Nombre =======");	
	System.out.println("Min y Max " + repository.getMinMaxLengthName());
}

@Transactional(readOnly = true)
public void DevolverAgregacion(){
	System.out.println("=====Consulta Agregacion =======");
	List<String> names = repository.DevolverAgregacion();
	names.forEach(System.out::println);

	System.out.println("=====Consulta Nombre y Largo =======");

	List<Object[]> personreg = repository.getPersonNameLength();
	personreg.forEach(reg -> {
		System.out.println("Longitud nombre= " + reg[1] + ", nombre=" + reg[0]);
	});

}

	@Transactional(readOnly = true)
	public void personalizeQueriesGetAll() {
		System.out.println("=====Consulta con Between=======");
		List<Person> names = repository.findAllByOrderByNameDesc();
		names.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizeQueriesBetween() {
		System.out.println("=====Consulta con Between=======");
		List<String> names = repository.finAllBetweenId();
		names.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizeQueriesConcatUpperAndLowerCase() {
		System.out.println("=====Consulta con nombre de personas=======");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizeQueriesDistinct() {
		System.out.println("=====Consulta con nombre de personas=======");
		List<String> names = repository.finAllNames();
		names.forEach(System.out::println);

		System.out.println("=====Consulta con nombre de personas unicos =======");
		List<String> names1 = repository.finAllNamesDistinct();
		names1.forEach(System.out::println);

		System.out.println("=====Consulta con lenguaje de programacion unicos =======");
		List<String> names2 = repository.finAllprogrammingLanguajeDistinct();
		names2.forEach(System.out::println);

		System.out.println("Cantidad lenguajes: " + repository.countAllprogrammingLanguajeDistinct());

	}

	@Transactional(readOnly = true)
	public void personalizeQueries2() {
		System.out.println("=====Consulta por objeto persona y lenguaje=======");
		List<Object[]> personreg = repository.findAllMixPersona();
		personreg.forEach(reg -> {
			System.out.println("programinglanguaje= " + reg[1] + ", person=" + reg[0]);
		});
		System.out.println("=====Consulta que puebla y devuelve un entity=======");
		List<Person> persons = repository.findAllPersonalizedPerson();
		persons.forEach(System.out::println);

		System.out.println("=====Consulta que puebla y devuelve un clase personalizada=======");
		List<PersonDto> personDto = repository.findAllPersonDto();
		personDto.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizeQueries() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====Consulta por del nombre por el id=======");
		System.out.println("Ingres el id para el nombre");
		Long id = scanner.nextLong();
		scanner.close();
		Optional<Object> Optpersonrg = repository.obtenerPersonaDataById(id);
		Object[] personrg = (Object[]) Optpersonrg.orElseThrow();
		System.out.println("Nombre " + personrg[1]);

	}

	@Transactional
	public void delete2() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id persona");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresent(person -> {
			System.out.println(person);
			repository.delete(person);
			repository.findAll().forEach(System.out::println);
		});
		scanner.close();
	}

	@Transactional
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id persona");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		scanner.close();
	}

	
	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		// optionalPerson.ifPresent(person -> {
		if (optionalPerson.isPresent()) {
			Person personDB = optionalPerson.orElseThrow();	

			// System.out.println(personDB);
			System.out.println("Ingrese el lenguaje de programacion:");
			String programmingLanguage = scanner.next();
			personDB.setProgrammingLanguaje(programmingLanguage);
			Person personUpdated = repository.save(personDB);
			System.out.println(personUpdated);
		} else {
			System.out.println("El usuario no esta presente! no existe!");
		}

		// });

		scanner.close();
	}

	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre");
		String name = scanner.next();
		System.out.println("Ingrese el apellido");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje");
		String programmingLanguaje = scanner.next();
		scanner.close();

		Person person = new Person(name, lastname, programmingLanguaje);
		Person personNew = repository.save(person);
		System.out.println(personNew);
		repository.findById(personNew.getId()).ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void finOne() {
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);
		// if (optionalPerson.isPresent()){
		// if (!optionalPerson.isEmpty()){
		// person =optionalPerson.get();
		// }
		// System.out.println(person);
		repository.findByNameContaining("pe").ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();

		List<Person> persons = (List<Person>) repository.findByProgrammingLanguaje("Python");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValues = repository.obtenerPersonaData("Java");
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
			// System.out.println(person);
		});
	}

}
