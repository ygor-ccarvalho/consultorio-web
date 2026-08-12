package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.model.AnotacaoPaciente;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnotacaoPacienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCriacao;

	@NotBlank(groups = CreateGroup.class, message = "O campo Observação é obrigatório")
	private String observacao;

	private Long pacienteId;

	public AnotacaoPacienteDTO(AnotacaoPaciente obj) {
		this.id = obj.getId();
		this.dataCriacao = obj.getDataCriacao();
		this.observacao = obj.getObservacao();
		this.pacienteId = obj.getPaciente().getId();
	}

}
