package com.ygor.web_consultorio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ygor.web_consultorio.dto.PacienteDTO;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.PacienteRepository;
import com.ygor.web_consultorio.service.exception.DataBindingViolationException;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

	@Mock
	private PacienteRepository repository;

	@InjectMocks
	private PacienteService service;

	private PacienteDTO dto;

	@BeforeEach
	void setup() {
		dto = new PacienteDTO();
		dto.setNome("Ygor");
		dto.setCpf("256.427.210-03");
		dto.setEmail("ygor@mail.com");
		dto.setTelefone("32972255068");
		dto.setConvenio("Unimed");

	}

	@Test
	void deveCriarPacienteAtivo() {
		dto.setAtivo(false);

		when(repository.findByCpf(any())).thenReturn(Optional.empty());

		when(repository.save(any(Paciente.class))).thenAnswer(chamada -> chamada.getArgument(0));

		PacienteDTO salvo = service.create(dto);

		assertTrue(salvo.getAtivo());
	}

	@Test
	void devePermitirQuandoForOMesmoPaciente() {

		Paciente pacienteExistente = new Paciente();
		pacienteExistente.setId(1L);
		dto.setId(1L);

		when(repository.findByCpf(any())).thenReturn(Optional.of(pacienteExistente));

		assertDoesNotThrow(() -> service.validaPorCpf(dto));
	}

	@Test
	void deveInativarAoInvesDeDeletar() {
		Paciente paciente = new Paciente();
		paciente.setId(1L);
		paciente.setAtivo(true);

		when(repository.findById(1L)).thenReturn(Optional.of(paciente));

		service.delete(1L);

		assertFalse(paciente.getAtivo());
		verify(repository).save(paciente);
		verify(repository, never()).deleteById(any());
	}

	@Test
	void deveLancarErroQuandoCpfDuplicado() {
		Paciente paciente = new Paciente();
		paciente.setId(1L);

		when(repository.findByCpf(any())).thenReturn(Optional.of(paciente));

		assertThrows(DataBindingViolationException.class, () -> service.validaPorCpf(dto));
	}

	@Test
	void deveLancarErroQuandoPacienteNaoExiste() {

		when(repository.findById(999L)).thenReturn(Optional.empty());

		assertThrows(ObjectNotFoundException.class, () -> service.findById(999L));
	}

}
