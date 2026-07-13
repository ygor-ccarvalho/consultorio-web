package com.ygor.web_consultorio.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ygor.web_consultorio.dto.ConsultaDTO;
import com.ygor.web_consultorio.enums.StatusConsulta;
import com.ygor.web_consultorio.enums.TipoConsulta;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	private Integer duracaoMinutos;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusConsulta status; 
	
	@Enumerated(EnumType.STRING)
	private TipoConsulta tipo;
	
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
		
	public Consulta(ConsultaDTO obj) {
        super();
        this.id = obj.getId();
        this.dataHora = obj.getDataHora();
        this.duracaoMinutos = obj.getDuracaoMinutos();
        this.valor = obj.getValor();
        this.status = obj.getStatus();
        this.tipo = obj.getTipo();
        this.observacoes = obj.getObservacoes();
    }
}
	
