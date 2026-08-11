package com.ygor.web_consultorio.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ygor.web_consultorio.dto.PacienteDTO;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.PacienteRepository;
import com.ygor.web_consultorio.service.exception.DataBindingViolationException;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class PacienteServiceIT {

	@Autowired
	private PacienteService service;

	@Autowired
	private PacienteRepository repository;

	private Paciente paciente;

	@BeforeEach
	void setUp() {

		paciente = new Paciente();

		paciente.setNome("Natuza Nery");
		paciente.setCpf("397.126.380-14");
		paciente.setEmail("natuza@mail.com");
		paciente.setSexo("FEMININO");
		paciente.setEndereco("Rua Adolfo Olinto, 165 - Centro, Pouso Alegre - MG");
		paciente.setDataNascimento(LocalDate.of(1990, 04, 10));
		paciente.setTelefone("35991234567");
		paciente.setConvenio("Unimed");
		paciente.setProfissao("Reporter");
		paciente.setNomePai("Neuber Nery");
		paciente.setNomeMae("Dilene Barrêto");
	}

	@Test
	void deveCriarPacienteEPersistirNoBanco() {

		PacienteDTO salvo = service.create(new PacienteDTO(paciente));
		assertNotNull(salvo.getId());

		Optional<Paciente> doBanco = repository.findById(salvo.getId());
		assertTrue(doBanco.isPresent());
		assertTrue(doBanco.get().getAtivo());

	}

	@Test
	void deveInativarInvesDeDeletar() {

		PacienteDTO salvo = service.create(new PacienteDTO(paciente));

		service.delete(salvo.getId());

		Optional<Paciente> doBanco = repository.findById(salvo.getId());
		assertTrue(doBanco.isPresent());
		assertFalse(doBanco.get().getAtivo());
	}

	@Test
	void deveLancarErroQuandoCpfDuplicado() {

		service.create(new PacienteDTO(paciente));

		paciente.setNome("Yuri Gonçalves");
		paciente.setEmail("yuri@mail.com");

		assertThrows(DataBindingViolationException.class, () -> service.create(new PacienteDTO(paciente)));
	}

	@Test
	void deveLancarErroQuandoPacienteInexistente() {

		assertThrows(ObjectNotFoundException.class, () -> service.findById(999L));

	}
}