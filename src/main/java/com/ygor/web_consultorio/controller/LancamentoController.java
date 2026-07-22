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

import com.ygor.web_consultorio.dto.LancamentoDTO;
import com.ygor.web_consultorio.dto.groups.CreateGroup;
import com.ygor.web_consultorio.dto.groups.UpdateGroup;
import com.ygor.web_consultorio.service.LancamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lancamentos")
@RequiredArgsConstructor
public class LancamentoController {

	private final LancamentoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> findById(@PathVariable Long id) {
		LancamentoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<LancamentoDTO>> findAll() {
		List<LancamentoDTO> listDTO = service.findAll();
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<LancamentoDTO> create(@Validated(CreateGroup.class) @RequestBody LancamentoDTO objDTO) {
		LancamentoDTO newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> update(@PathVariable long id,
			@Validated(UpdateGroup.class) @RequestBody LancamentoDTO objDTO) {
		return ResponseEntity.ok().body(service.update(id, objDTO));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
