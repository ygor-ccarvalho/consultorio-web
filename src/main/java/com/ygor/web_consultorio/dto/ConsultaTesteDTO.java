package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.model.ConsultaTeste;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ConsultaTesteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(groups = CreateGroup.class, message = "O campo Teste Id é obrigatório.")
	private Long testeId;

	@Positive(groups = CreateGroup.class)
	@NotNull(groups = CreateGroup.class, message = "O campo quantidade é obrigatório.")
	private Integer qtd;

	private BigDecimal custoUnitario;
	private Long consultaId;
	private String nomeTeste;
	private BigDecimal custoTotal;

	public ConsultaTesteDTO(ConsultaTeste obj) {
		super();
		this.id = obj.getId();
		this.testeId = obj.getTeste().getId();
		this.qtd = obj.getQtd();
		this.custoUnitario = obj.getCustoUnitario();
		this.consultaId = obj.getConsulta().getId();
		this.nomeTeste = obj.getTeste().getNome();
		this.custoTotal = obj.getCustoUnitario().multiply(new BigDecimal(obj.getQtd()));
	}

}
