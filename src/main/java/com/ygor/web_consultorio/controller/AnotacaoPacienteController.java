package com.ygor.web_consultorio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ygor.web_consultorio.dto.AnotacaoPacienteDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.service.AnotacaoPacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/pacientes/{id}/anotacoes")
@RequiredArgsConstructor
public class AnotacaoPacienteController {
	private final AnotacaoPacienteService service;

	@GetMapping
	public ResponseEntity<List<AnotacaoPacienteDTO>> findByPaciente(@PathVariable Long id) {
		return ResponseEntity.ok(service.findByPaciente(id));
	}

	@PostMapping
	public ResponseEntity<AnotacaoPacienteDTO> create(
		@PathVariable Long id, 
		@Validated(CreateGroup.class) @RequestBody AnotacaoPacienteDTO objDTO) {
		AnotacaoPacienteDTO newObj = service.create(id, objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

}
