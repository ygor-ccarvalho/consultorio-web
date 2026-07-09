package com.ygor.web_consultorio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ygor.web_consultorio.dto.PacienteDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/pacientes")
@RequiredArgsConstructor
public class PacienteController {
	
	private final PacienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable Long id){
		PacienteDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<PacienteDTO>> findAll(){
		List<PacienteDTO> listDTO = service.findAll();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<PacienteDTO> create(@Validated(CreateGroup.class) @RequestBody PacienteDTO objDTO){
		PacienteDTO newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> update(@PathVariable Long id,
			@Validated(UpdateGroup.class) @RequestBody PacienteDTO objDTO){
		return ResponseEntity.ok().body(service.update(id, objDTO));
	}

	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
