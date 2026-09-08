package com.ygor.web_consultorio.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.AvaliacaoDTO;
import com.ygor.web_consultorio.enums.StatusAvaliacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })

public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@Enumerated(EnumType.STRING)
	private StatusAvaliacao status;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLaudo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDevolutiva;
	
	private Boolean ativo;

	public Avaliacao(AvaliacaoDTO obj) {
		super();
		this.id = obj.getId();
		this.dataInicio = obj.getDataInicio();
		this.dataLaudo = obj.getDataLaudo();
		this.dataDevolutiva = obj.getDataDevolutiva();
	}

}
