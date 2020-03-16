package br.com.rostirolla.restmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rostirolla.restmicroservice.model.Pessoa;
import br.com.rostirolla.restmicroservice.repository.PessoaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

//Exemplos de anotações no seguinte endereço: https://github.com/springfox/springfox/blob/master/springfox-spring-web/src/test/java/springfox/documentation/spring/web/dummy/controllers/FeatureDemonstrationService.java#L243-267
@Controller
@Api(description = "CRUD de Pessoas.")
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@ApiOperation(value = "Pesquisa todas as pessoas")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Pessoa>> listPessoas() {
		return new ResponseEntity<List<Pessoa>>(pessoaRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Pesquisa uma pessoa pelo seu id")
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable("id") Long id) {
		if (pessoaRepository.findById(id).isPresent())
			return new ResponseEntity<Pessoa>(pessoaRepository.findById(id).get(), new HttpHeaders(), HttpStatus.OK);
		return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Pesquisa pessoas pelo nome")
	@GetMapping(path = "/nome/{nome}", produces = "application/json")
	public ResponseEntity<List<Pessoa>> getPessoasByNome(@PathVariable("nome") String nome) {
		return new ResponseEntity<List<Pessoa>>(pessoaRepository.findAllByNome(nome), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Lista tabela com todas as pessoa")
	@GetMapping("/tabela")
	public String tablePessoas(Model model) {
		model.addAttribute("pessoas", pessoaRepository.findAll());
		return "pessoas";
	}

	@ApiOperation(value = "Cadastra uma pessoa")
	@PostMapping(produces = "application/json")
	public ResponseEntity<Pessoa> addPessoa(@RequestBody Pessoa pessoa) {
		return new ResponseEntity<Pessoa>(pessoaRepository.save(pessoa), new HttpHeaders(), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Exclui uma pessoa pelo seu id")
	@DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Pessoa> deletePessoa(@PathVariable("id") Long id) {
		pessoaRepository.deleteById(id);
		return new ResponseEntity<Pessoa>(HttpStatus.OK);
	}

	@ApiOperation(value = "Altera uma pessoa")
	@PutMapping(produces = "application/json")
	public ResponseEntity<Pessoa> putPessoa(@RequestBody Pessoa pessoa) throws NotFoundException {
		if (pessoa.getId() == null || !pessoaRepository.existsById(pessoa.getId()))
			throw new NotFoundException("Id inválido ou inexistente.");
		return new ResponseEntity<Pessoa>(pessoaRepository.save(pessoa), new HttpHeaders(), HttpStatus.OK);
	}
}