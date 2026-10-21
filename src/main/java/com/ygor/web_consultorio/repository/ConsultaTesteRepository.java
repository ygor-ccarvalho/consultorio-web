package com.ygor.web_consultorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.model.ConsultaTeste;

public interface ConsultaTesteRepository extends JpaRepository<ConsultaTeste, Long> {
	List<ConsultaTeste> findByConsultaId(Long consultaId);
}
