package com.ygor.web_consultorio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.dto.AnotacaoPacienteDTO;
import com.ygor.web_consultorio.model.AnotacaoPaciente;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.AnotacaoPacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AnotacaoPacienteService {

	private final AnotacaoPacienteRepository repository;
	private final PacienteService pacienteService;

	public List<AnotacaoPacienteDTO> findByPaciente(Long pacienteId) {
		Paciente p = pacienteService.buscarEntidade(pacienteId);
		return repository.findByPacienteOrderByDataCriacaoDesc(p).stream().map(AnotacaoPacienteDTO::new).toList();
	}

	public AnotacaoPacienteDTO create(Long pacienteId, AnotacaoPacienteDTO objDTO) {
		Paciente p = pacienteService.buscarEntidade(pacienteId);
		AnotacaoPaciente a = new AnotacaoPaciente(objDTO);
		a.setPaciente(p);
		a.setDataCriacao(LocalDateTime.now());
		a.setId(null);
		return new AnotacaoPacienteDTO(repository.save(a));
	}

}
