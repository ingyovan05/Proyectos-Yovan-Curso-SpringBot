package com.yovan.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.yovan.curso.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyBidireccionalFind();
	}


	@Transactional
	public void manyToManyBidireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course1);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(2L);
			if(courseOptionalDb.isPresent()){
				Course courseDB = courseOptionalDb.get();
				studentDb.removeCourse(courseDB);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@Transactional
	public void manyToManyBidireccionalRemove() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de sprint boot", "Andres");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course1);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);
			if(courseOptionalDb.isPresent()){
				Course courseDB = courseOptionalDb.get();
				studentDb.removeCourse(courseDB);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}


	
	@Transactional
	public void manyToManyBidireccional() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de sprint boot", "Andres");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course1);

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		

	}


	@Transactional
	public void manyToManyRemove() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de sprint boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);
			if(courseOptionalDb.isPresent()){
				Course courseDB = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDB);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

		}

	}

	@Transactional
	public void manyToManyRemoveFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);
			if(courseOptionalDb.isPresent()){
				Course courseDB = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDB);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

		}

	}

	@Transactional
	public void manyToManyFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de sprint boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {
		Optional<Client> clientOptional = clientRepository.findOneWithInvoices(2L);
		clientOptional.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);
			client.setClientedDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOneBidireccional() {
		Client client = new Client("Fran", "Moras");
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		client.setClientedDetails(clientDetails);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindByID() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOneWithInvoices(2L);

		clientOptional.ifPresent(client -> {
			client.setClientedDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Fran", "Moras");
		client.setClientedDetails(clientDetails);
		clientRepository.save(client);
		System.out.println(client);

	}

	@Transactional
	public void removeInvoiceBidireccional() {

		Optional<Client> optionalCliente = Optional.of(new Client("Fran", "Moras"));

		optionalCliente.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras casa", 5000L);
			Invoice invoice2 = new Invoice("Compras oficina", 8000L);
			client.AddInvoice(invoice1).AddInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});

		Optional<Client> optionalClienteDb = clientRepository.findOneWithInvoices(3L);
		optionalClienteDb.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFinById() {

		Optional<Client> optionalCliente = clientRepository.findOneWithInvoices(1L);
		optionalCliente.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras casa", 5000L);
			Invoice invoice2 = new Invoice("Compras oficina", 8000L);
			client.AddInvoice(invoice1).AddInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});

		Optional<Client> optionalClienteDb = clientRepository.findOneWithInvoices(1L);
		optionalClienteDb.ifPresent(client -> {
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {
		Optional<Client> optionalCliente = clientRepository.findOneWithInvoices(1L);
		optionalCliente.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras casa", 5000L);
			Invoice invoice2 = new Invoice("Compras oficina", 8000L);
			client.AddInvoice(invoice1).AddInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");
		Invoice invoice1 = new Invoice("Compras casa", 5000L);
		Invoice invoice2 = new Invoice("Compras oficina", 8000L);
		client.AddInvoice(invoice1).AddInvoice(invoice2);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);

			System.out.println("Nuevamente consulta");
			System.out.println(optionalClient2);
			System.out.println("Nuevamente consulta Direccion 2");
			System.out.println(address2);

			optionalClient2.ifPresent(c -> {

				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			client.setAddresses(Arrays.asList(address1, address2));

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}
}
