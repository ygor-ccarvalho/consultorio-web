package com.ygor.web_consultorio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.dto.PacienteDTO;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.PacienteRepository;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

	private final PacienteRepository repository;
	
	public List<PacienteDTO> findAll() {
		return repository.findAll().stream()
				.map(PacienteDTO::new)
				.toList();
	}
	
	public PacienteDTO findById(Long id) {
		return new PacienteDTO(buscarEntidade(id));
		}
	
	public Paciente buscarEntidade(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado: " + id));
	}
	
}
