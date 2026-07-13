package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.enums.StatusConsulta;
import com.ygor.web_consultorio.enums.TipoConsulta;
import com.ygor.web_consultorio.model.Consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsultaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Data e Hora é obrigatório")
	@Future(groups = CreateGroup.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Duração é obrigatório")
	@Positive(groups = {CreateGroup.class, UpdateGroup.class})
	private Integer duracaoMinutos;
	
	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Valor é obrigatório")
	@PositiveOrZero(groups = {CreateGroup.class, UpdateGroup.class})
	private BigDecimal valor;
	
	private StatusConsulta status; 
	
	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Tipo da Consulta é obrigatório")
	private TipoConsulta tipo;
	
	private String observacoes;
	
	@NotNull(groups = CreateGroup.class, message = "O campo Paciente é obrigatório")
	private Long pacienteId;

	
	public ConsultaDTO(Consulta obj) {
		this.id = obj.getId();
		this.dataHora = obj.getDataHora();
		this.duracaoMinutos = obj.getDuracaoMinutos();
		this.valor = obj.getValor();
		this.status = obj.getStatus();
		this.tipo = obj.getTipo();
		this.observacoes = obj.getObservacoes();
		this.pacienteId = obj.getPaciente().getId();
		
	}
}
