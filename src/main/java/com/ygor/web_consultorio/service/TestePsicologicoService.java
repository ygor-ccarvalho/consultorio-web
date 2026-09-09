package com.ygor.web_consultorio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ygor.web_consultorio.dto.TestePsicologicoDTO;
import com.ygor.web_consultorio.model.TestePsicologico;
import com.ygor.web_consultorio.repository.TestePsicologicoRepository;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestePsicologicoService {
	private final TestePsicologicoRepository repository;

	public List<TestePsicologicoDTO> findAll() {
		return repository.findAll().stream().map(TestePsicologicoDTO::new).toList();
	}

	public List<TestePsicologicoDTO> findByAtivoTrue() {
		return repository.findByAtivo(true).stream().map(TestePsicologicoDTO::new).toList();
	}

	public List<TestePsicologicoDTO> findByAtivoFalse() {
		return repository.findByAtivo(false).stream().map(TestePsicologicoDTO::new).toList();
	}

	public TestePsicologicoDTO findById(Long id) {
		return new TestePsicologicoDTO(buscarEntidade(id));
	}

	public TestePsicologicoDTO create(TestePsicologicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setAtivo(true);
		TestePsicologico newObj = repository.save(new TestePsicologico(objDTO));
		return new TestePsicologicoDTO(newObj);
	}

	public TestePsicologicoDTO update(Long id, TestePsicologicoDTO objDTO) {
		TestePsicologico obj = buscarEntidade(id);
		objDTO.setId(id);
		objDTO.setAtivo(obj.getAtivo());
		obj = new TestePsicologico(objDTO);
		return new TestePsicologicoDTO(repository.save(obj));
	}

	public void delete(Long id) {
		TestePsicologico obj = buscarEntidade(id);
		obj.setAtivo(false);
		repository.save(obj);
	}

	public TestePsicologico buscarEntidade(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Teste Não encontrado, ID: " + id));
	}
}
