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

import com.ygor.web_consultorio.dto.ConsultaDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.service.ConsultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/consultas")
@RequiredArgsConstructor
public class ConsultaController {

	private final ConsultaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ConsultaDTO> findById(@PathVariable Long id) {
		ConsultaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<ConsultaDTO>> findAll() {
		List<ConsultaDTO> listDTO = service.findAll();
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<ConsultaDTO> create(@Validated(CreateGroup.class) @RequestBody ConsultaDTO objDTO) {
		ConsultaDTO newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ConsultaDTO> update(@PathVariable Long id,
			@Validated(UpdateGroup.class) @RequestBody ConsultaDTO objDTO) {
		return ResponseEntity.ok().body(service.update(id, objDTO));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
