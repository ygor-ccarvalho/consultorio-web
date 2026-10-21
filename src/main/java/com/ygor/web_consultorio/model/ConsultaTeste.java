package com.ygor.web_consultorio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consulta_teste")
@Setter
@Getter
@NoArgsConstructor

public class ConsultaTeste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teste_id")
	private TestePsicologico teste;

	private Integer qtd;

	@Column(name = "custo_unitario", precision = 10, scale = 2)
	private BigDecimal custoUnitario;
}
