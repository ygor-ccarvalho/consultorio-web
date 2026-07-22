package com.ygor.web_consultorio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.dto.LancamentoDTO;
import com.ygor.web_consultorio.model.Lancamento;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.LancamentoRepository;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LancamentoService {

	private final LancamentoRepository repository;
	private final PacienteService pacienteService;

	public List<LancamentoDTO> findAll() {
		return repository.findAll().stream().map(LancamentoDTO::new).toList();
	}

	public LancamentoDTO findById(Long id) {
		return new LancamentoDTO(buscarEntidade(id));

	}

	public LancamentoDTO create(LancamentoDTO objDTO) {
		Lancamento l = new Lancamento(objDTO);
		l.setId(null);
		if (objDTO.getPacienteId() != null) {
			l.setPaciente(pacienteService.buscarEntidade(objDTO.getPacienteId()));
		}
		return new LancamentoDTO(repository.save(l));
	}

	public LancamentoDTO update(Long id, LancamentoDTO objDTO) {
		Lancamento obj = buscarEntidade(id);
		obj.setData(objDTO.getData());
		obj.setDescricao(objDTO.getDescricao());
		obj.setValor(objDTO.getValor());
		obj.setTipo(objDTO.getTipo());
		if (objDTO.getPacienteId() != null) {
			Paciente p = pacienteService.buscarEntidade(objDTO.getPacienteId());
			obj.setPaciente(p);
		} else {
			obj.setPaciente(null);
		}
		return new LancamentoDTO(repository.save(obj));
	}

	public void delete(Long id) {
		buscarEntidade(id);
		repository.deleteById(id);
	}

	private Lancamento buscarEntidade(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Lançamento não encontrado: " + id));

	}
}
