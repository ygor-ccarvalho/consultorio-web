package com.ygor.web_consultorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ygor.web_consultorio.model.TestePsicologico;

public interface TestePsicologicoRepository extends JpaRepository<TestePsicologico, Long> {

	List<TestePsicologico> findByAtivo(Boolean ativo);
}
