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

import com.ygor.web_consultorio.dto.AvaliacaoDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.service.AvaliacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/pacientes/{pacienteId}/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {
	private final AvaliacaoService service;

	@GetMapping("/ativas")
	public ResponseEntity<List<AvaliacaoDTO>> findByPacienteAtivas(@PathVariable Long pacienteId) {
		return ResponseEntity.ok(service.findByPacienteAtivas(pacienteId));
	}

	@GetMapping("/inativas")
	public ResponseEntity<List<AvaliacaoDTO>> findByPacienteInativas(@PathVariable Long pacienteId) {
		return ResponseEntity.ok(service.findByPacienteInativas(pacienteId));
	}

	@PostMapping
	public ResponseEntity<AvaliacaoDTO> create(
		@PathVariable Long pacienteId,
		@Validated(CreateGroup.class) @RequestBody AvaliacaoDTO objDTO){
		AvaliacaoDTO newObj = service.create(pacienteId, objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PutMapping("/{avaliacaoId}")
	public ResponseEntity<AvaliacaoDTO> update(
			@PathVariable Long avaliacaoId, 
			@Validated(UpdateGroup.class) @RequestBody AvaliacaoDTO objDTO)	{
		return ResponseEntity.ok().body(service.update(avaliacaoId, objDTO));
	}
	
	@DeleteMapping("/{avaliacaoId}")
	public ResponseEntity<Void> delete(@PathVariable Long avaliacaoId){
		service.delete(avaliacaoId);
		return ResponseEntity.noContent().build();
	}
}
