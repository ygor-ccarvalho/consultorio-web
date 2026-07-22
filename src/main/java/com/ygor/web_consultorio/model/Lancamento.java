package com.ygor.web_consultorio.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.LancamentoDTO;
import com.ygor.web_consultorio.enums.TipoLancamento;

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
@EqualsAndHashCode(of = "id")

public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private BigDecimal valor;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;

	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = true)
	private Paciente paciente;

	public Lancamento(LancamentoDTO obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.data = obj.getData();
		this.tipo = obj.getTipo();
	}

}
