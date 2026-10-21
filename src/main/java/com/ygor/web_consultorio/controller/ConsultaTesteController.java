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

import com.ygor.web_consultorio.dto.ConsultaTesteDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.service.ConsultaTesteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/consultas/{id}/testes")
@RequiredArgsConstructor
public class ConsultaTesteController {
	private final ConsultaTesteService service;

	@GetMapping
	public ResponseEntity<List<ConsultaTesteDTO>> findByConsulta(@PathVariable Long id) {
		return ResponseEntity.ok(service.findByConsulta(id));
	}

	@PostMapping
	public ResponseEntity<ConsultaTesteDTO> create(@PathVariable Long id,
			@Validated(CreateGroup.class) @RequestBody ConsultaTesteDTO objDTO) {
		ConsultaTesteDTO newObj = service.create(id, objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
}
