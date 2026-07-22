package com.ygor.web_consultorio.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.enums.TipoLancamento;
import com.ygor.web_consultorio.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	List<Lancamento> findByPacienteId(Long id);

	List<Lancamento> findByDataBetween(LocalDate inicio, LocalDate fim);
	
	List<Lancamento> findByTipoAndDataBetween(TipoLancamento tipo, LocalDate inicio, LocalDate fim);
}
