package com.ygor.web_consultorio.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	List<Consulta> findByPacienteId(Long id);
	Optional<Consulta> findByDataHora(LocalDateTime dataHora);
}
