package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.enums.StatusAvaliacao;
import com.ygor.web_consultorio.model.Avaliacao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AvaliacaoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
		
	private Long pacienteId;
	
	@NotNull(groups = UpdateGroup.class, message = "O campo Status da Avaliação é obrigatório.")
	private StatusAvaliacao status;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLaudo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDevolutiva;
	
	

	public AvaliacaoDTO (Avaliacao obj) {
		this.id = obj.getId();
		this.dataInicio = obj.getDataInicio();
		this.pacienteId = obj.getPaciente().getId();
		this.status = obj.getStatus();
		this.dataDevolutiva = obj.getDataDevolutiva();
		this.dataLaudo = obj.getDataLaudo();
		
	}
	
}
