package com.ygor.web_consultorio.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.dto.AvaliacaoDTO;
import com.ygor.web_consultorio.enums.StatusAvaliacao;
import com.ygor.web_consultorio.model.Avaliacao;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.AvaliacaoRepository;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AvaliacaoService {

	private final AvaliacaoRepository repository;
	private final PacienteService pacienteService;

	public List<AvaliacaoDTO> findByPacienteAtivas(Long pacienteId) {
		return repository.findByPacienteIdAndAtivoTrue(pacienteId).stream().map(AvaliacaoDTO::new).toList();
	}

	public List<AvaliacaoDTO> findByPacienteInativas(Long pacienteId) {
		return repository.findByPacienteIdAndAtivoFalse(pacienteId).stream().map(AvaliacaoDTO::new).toList();
	}

	public AvaliacaoDTO create(Long pacienteId, AvaliacaoDTO objDTO) {
		Paciente p = pacienteService.buscarEntidade(pacienteId);
		Avaliacao a = new Avaliacao(objDTO);
		a.setAtivo(true);
		a.setPaciente(p);
		a.setDataInicio(LocalDate.now());
		a.setStatus(StatusAvaliacao.EM_ANDAMENTO);
		a.setId(null);
		return new AvaliacaoDTO(repository.save(a));
	}

	public AvaliacaoDTO update(Long id, AvaliacaoDTO objDTO) {
		Avaliacao obj = buscarEntidade(id);
		obj.setStatus(objDTO.getStatus());

		if (objDTO.getStatus() == StatusAvaliacao.AGUARDANDO_LAUDO) {
			obj.setDataLaudo(LocalDate.now());
		} else if (objDTO.getStatus() == StatusAvaliacao.CONCLUIDA) {
			obj.setDataDevolutiva(LocalDate.now());
		}

		return new AvaliacaoDTO(repository.save(obj));
	}

	public void delete(Long id) {
		Avaliacao obj = buscarEntidade(id);
		obj.setAtivo(false);
		repository.save(obj);
	}

	public Avaliacao buscarEntidade(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Avaliação não encontrada: " + id));
	}
}
