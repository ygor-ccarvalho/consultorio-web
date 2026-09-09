package com.ygor.web_consultorio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ygor.web_consultorio.dto.TestePsicologicoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TestePsicologico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Integer qtdEstoque;
	
	private Integer estoqueMinimo;
	
	private BigDecimal custoUnitario;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	public TestePsicologico(TestePsicologicoDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.qtdEstoque = obj.getQtdEstoque();
		this.estoqueMinimo = obj.getEstoqueMinimo();
		this.custoUnitario = obj.getCustoUnitario();
		this.ativo = obj.getAtivo();
	}


}
