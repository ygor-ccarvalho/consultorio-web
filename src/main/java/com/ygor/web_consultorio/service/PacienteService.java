package com.ygor.web_consultorio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.dto.PacienteDTO;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.PacienteRepository;
import com.ygor.web_consultorio.service.exception.DataBindingViolationException;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

	private final PacienteRepository repository;

	public List<PacienteDTO> findAll() {
		return repository.findAll().stream().map(PacienteDTO::new).toList();
	}

	public List<PacienteDTO> findByAtivoTrue() {
		return repository.findByAtivo(true).stream().map(PacienteDTO::new).toList();
	}

	public List<PacienteDTO> findByAtivoFalse() {
		return repository.findByAtivo(false).stream().map(PacienteDTO::new).toList();
	}

	public PacienteDTO findById(Long id) {
		return new PacienteDTO(buscarEntidade(id));
	}

	public PacienteDTO create(PacienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setAtivo(true);
		validaPorCpf(objDTO);
		Paciente newObj = repository.save(new Paciente(objDTO));
		return new PacienteDTO(newObj);
	}

	public PacienteDTO update(Long id, PacienteDTO objDTO) {
		Paciente obj = buscarEntidade(id);
		objDTO.setId(id);
		validaPorCpf(objDTO);
		objDTO.setAtivo(obj.getAtivo());
		objDTO.setDataCriacao(obj.getDataCriacao());
		obj = new Paciente(objDTO);
		return new PacienteDTO(repository.save(obj));
	}

	public void delete(Long id) {
		Paciente obj = buscarEntidade(id);
		obj.setAtivo(false);
		repository.save(obj);
	}

	public Paciente buscarEntidade(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado: " + id));
	}

	 void validaPorCpf(PacienteDTO objDTO) {
		Optional<Paciente> obj = repository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
			throw new DataBindingViolationException("CPF já cadastrado no sistema!");
		}
	}
	 
	 public List<PacienteDTO> aniversariantes(int dias) {
		    LocalDate hoje = LocalDate.now();
		    LocalDate limite = hoje.plusDays(dias);

		    return findByAtivoTrue().stream()
		            .filter(paciente -> {
		                LocalDate proximo = proximoAniversario(paciente.getDataNascimento(), hoje);
		                return !proximo.isBefore(hoje) && !proximo.isAfter(limite);
		            })
		            .toList();
		}
	 
	 private LocalDate proximoAniversario(LocalDate nascimento, LocalDate hoje) {
		    LocalDate aniversario = nascimento.withYear(hoje.getYear());
		    if (aniversario.isBefore(hoje)) {
		        aniversario = aniversario.plusYears(1);
		    }
		    return aniversario;
		}

}
