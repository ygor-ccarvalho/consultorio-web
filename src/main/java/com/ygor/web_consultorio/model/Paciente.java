package com.ygor.web_consultorio.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.PacienteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "cpf"})
public class Paciente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String telefone;
	
	@CPF(message = "CPF inválido")
	@Column(unique = true)
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();
	
	private String convenio;
	private String profissao;
	private String nomePai;
	private String nomeMae;
	private String sexo;
	
	public Paciente(Long id, String nome, String email, String endereco, String telefone, String cpf, String convenio, 
			String profissao, String nomePai, String nomeMae, String sexo) {
		this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.convenio = convenio;
        this.profissao = profissao;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.sexo = sexo;
	}
	
	
	public Paciente(PacienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.endereco = obj.getEndereco();
        this.telefone = obj.getTelefone();
        this.cpf = obj.getCpf();
        this.dataNascimento = obj.getDataNascimento();
        this.dataCriacao = obj.getDataCriacao();
        this.convenio = obj.getConvenio();
        this.profissao = obj.getProfissao();
        this.nomePai = obj.getNomePai();
        this.nomeMae = obj.getNomeMae();
        this.sexo = obj.getSexo();
        
    }

	
	

	
	
	
}