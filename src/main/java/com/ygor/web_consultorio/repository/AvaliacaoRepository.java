package com.ygor.web_consultorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
	
	List<Avaliacao> findByPacienteIdAndAtivoTrue(Long pacienteId);
	
	List<Avaliacao> findByPacienteIdAndAtivoFalse(Long pacienteId);


}
