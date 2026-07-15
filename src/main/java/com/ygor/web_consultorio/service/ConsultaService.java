package com.ygor.web_consultorio.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.repository.ConsultaRepository;
import com.ygor.web_consultorio.service.exception.DataBindingViolationException;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;
import com.ygor.web_consultorio.dto.ConsultaDTO;
import com.ygor.web_consultorio.enums.StatusConsulta;
import com.ygor.web_consultorio.model.Consulta;
import com.ygor.web_consultorio.model.Paciente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ConsultaService {

	private final ConsultaRepository repository;
	private final PacienteService pacienteService;
	
	public List<ConsultaDTO> findAll(){
		return repository.findAll().stream()
				.map(ConsultaDTO::new)
				.toList();
	}
	
	public ConsultaDTO findById(Long id) {
		return new ConsultaDTO(buscarEntidade(id));
	}
	
	public ConsultaDTO create(ConsultaDTO objDTO) {
		validaHorario(objDTO, null);
		Paciente p = pacienteService.buscarEntidade(objDTO.getPacienteId());
		Consulta c = new Consulta(objDTO);
		c.setPaciente(p);
		c.setStatus(StatusConsulta.AGENDADA);
		c.setId(null);
		return new ConsultaDTO(repository.save(c));
	}
	
	public ConsultaDTO update(Long id, ConsultaDTO objDTO) {
		validaHorario(objDTO, id);	
		Consulta obj = buscarEntidade(id);
		obj.setDataHora(objDTO.getDataHora());
		obj.setDuracaoMinutos(objDTO.getDuracaoMinutos());
		obj.setValor(objDTO.getValor());
		obj.setTipo(objDTO.getTipo());
		obj.setObservacoes(objDTO.getObservacoes());
		obj.setPaciente(pacienteService.buscarEntidade(objDTO.getPacienteId()));
		return new ConsultaDTO(repository.save(obj));
		
	}
	
	public void delete(Long id) {
		buscarEntidade(id);
		repository.deleteById(id);
	}
	
	private void validaHorario(ConsultaDTO objDTO, Long id) {
		Optional<Consulta> obj = repository.findByDataHora(objDTO.getDataHora());
		if (obj.isPresent() && !obj.get().getId().equals(id)) {
			throw new DataBindingViolationException("Horário indisponivel!");
		}
	}
	
	
	private Consulta buscarEntidade(Long id){
	return repository.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrada: " + id));

	}
}