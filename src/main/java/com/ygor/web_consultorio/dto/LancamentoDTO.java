package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.enums.TipoLancamento;
import com.ygor.web_consultorio.model.Lancamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LancamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;

	@NotNull(groups = { CreateGroup.class, UpdateGroup.class }, message = "O campo Data é obrigatório")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Descrição é obrigatório")
	private String descricao;

	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Valor é obrigatório")
	@Positive(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Valor deve ser positivo")
	private BigDecimal valor;

	@NotNull(groups = { CreateGroup.class, UpdateGroup.class }, message = "O campo Tipo é obrigatório")
	private TipoLancamento tipo;

	private Long pacienteId;
	
	public LancamentoDTO(Lancamento obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.tipo = obj.getTipo();
		if (obj.getPaciente() != null) {
		    this.pacienteId = obj.getPaciente().getId();
		}
	}

}
