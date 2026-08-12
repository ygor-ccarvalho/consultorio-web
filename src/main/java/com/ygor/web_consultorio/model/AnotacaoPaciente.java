package com.ygor.web_consultorio.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.AnotacaoPacienteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(of = "id")
public class AnotacaoPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCriacao;

	@Column(length = 5000)
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	public AnotacaoPaciente(AnotacaoPacienteDTO obj) {
		super();
		this.id = obj.getId();
		this.observacao = obj.getObservacao();
	}
}
