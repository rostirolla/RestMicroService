package br.com.rostirolla.restmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import br.com.rostirolla.restmicroservice.controller.PessoaController;
import br.com.rostirolla.restmicroservice.repository.PessoaRepository;

@SpringBootApplication
public class RestMicroServiceApplication implements CommandLineRunner {

	@Autowired
    private PessoaRepository pessoaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RestMicroServiceApplication.class, args);
	}
	
	@Override
    public void run(String... args) {
        // Create a couple of Book and BookCategory
//        bookCategoryRepository.save(new BookCategory("Category 1", new Book("Hello Koding 1"), new Book("Hello Koding 2")));
    }

}
