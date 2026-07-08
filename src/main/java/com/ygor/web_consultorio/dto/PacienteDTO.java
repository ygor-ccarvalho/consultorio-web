package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.model.Paciente;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo nome é obrigatório")
	protected String nome;

	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo CPF é obrigatório")
	protected String cpf;

	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Email é obrigatório")
	protected String email;
	
	private String sexo;
	private String endereco;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	
	@NotNull
	private String telefone;
	@NotNull
	private String convenio;
	
	private String profissao;
	private String nomePai;
	private String nomeMae;
	
	public PacienteDTO(Paciente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.sexo = obj.getSexo();
		this.endereco = obj.getEndereco();
		this.dataNascimento = obj.getDataNascimento();
		this.dataCriacao = obj.getDataCriacao();
		this.telefone = obj.getTelefone();
		this.convenio = obj.getConvenio();
		this.profissao = obj.getProfissao();
		this.nomePai = obj.getNomePai();
		this.nomeMae = obj.getNomeMae();
	}
}
