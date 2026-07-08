package com.ygor.web_consultorio.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "cpf"})
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@CPF(message = "CPF inválido")
	@Column(unique = true)
	private String cpf;
	private String sexo;
	private String endereco;
	private LocalDate dataNascimento;
	private String telefone;
	private String convenio;
	private String profissao;
	private String nomePai;
	private String nomeMae;
	
	
	
}