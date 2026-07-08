package com.ygor.web_consultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
