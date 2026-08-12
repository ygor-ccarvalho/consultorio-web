package com.ygor.web_consultorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.model.AnotacaoPaciente;
import com.ygor.web_consultorio.model.Paciente;

public interface AnotacaoPacienteRepository extends JpaRepository<AnotacaoPaciente, Long> {
	List<AnotacaoPaciente> findByPacienteOrderByDataCriacaoDesc(Paciente obj);

}
