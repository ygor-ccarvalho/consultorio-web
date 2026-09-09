package com.ygor.web_consultorio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.model.TestePsicologico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestePsicologicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo nome é obrigatório!")
	private String nome;
	
	@NotNull(groups = CreateGroup.class, message = "A quantidade em estoque é obrigatória")
	@PositiveOrZero(groups = {CreateGroup.class, UpdateGroup.class}, message = "O estoque não pode ser negativo")
	private Integer qtdEstoque;
	
	@NotNull(groups = CreateGroup.class, message = "A quantidade minima em estoque é obrigatória")
	@PositiveOrZero(groups = {CreateGroup.class, UpdateGroup.class}, message = "A quantidade minima do estoque não pode ser negativo")
	private Integer estoqueMinimo;
	
	@NotNull(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Valor é obrigatório")
	@Positive(groups = {CreateGroup.class, UpdateGroup.class}, message = "O campo Valor deve ser positivo")
	private BigDecimal custoUnitario;
	
	private Boolean ativo;
	
	public TestePsicologicoDTO(TestePsicologico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.qtdEstoque = obj.getQtdEstoque();
		this.estoqueMinimo = obj.getEstoqueMinimo();
		this.custoUnitario = obj.getCustoUnitario();
		this.ativo = obj.getAtivo();
	}
}
