package br.com.rostirolla.restmicroservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="idpessoa")
	@Getter
	private Long id;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private int idade;	
	
	
}
