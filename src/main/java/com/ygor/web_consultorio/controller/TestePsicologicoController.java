package com.ygor.web_consultorio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ygor.web_consultorio.dto.TestePsicologicoDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.service.TestePsicologicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/testes-psicologicos")
@RequiredArgsConstructor

public class TestePsicologicoController {

	private final TestePsicologicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TestePsicologicoDTO> findById(@PathVariable Long id) {
		TestePsicologicoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<TestePsicologicoDTO>> findAll() {
		List<TestePsicologicoDTO> listDTO = service.findAll();
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/ativos")
	public ResponseEntity<List<TestePsicologicoDTO>> findByAtivoTrue() {
		return ResponseEntity.ok().body(service.findByAtivoTrue());
	}

	@GetMapping(value = "/inativos")
	public ResponseEntity<List<TestePsicologicoDTO>> findByAtivoFalse() {
		return ResponseEntity.ok().body(service.findByAtivoFalse());
	}

	@PostMapping
	public ResponseEntity<TestePsicologicoDTO> create(
			@Validated(CreateGroup.class) @RequestBody TestePsicologicoDTO objDTO) {
		TestePsicologicoDTO newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TestePsicologicoDTO> update(@PathVariable Long id,
			@Validated(UpdateGroup.class) @RequestBody TestePsicologicoDTO objDTO) {
		return ResponseEntity.ok().body(service.update(id, objDTO));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
