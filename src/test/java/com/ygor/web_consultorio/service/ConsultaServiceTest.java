package com.ygor.web_consultorio.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ygor.web_consultorio.dto.ConsultaDTO;
import com.ygor.web_consultorio.enums.StatusConsulta;
import com.ygor.web_consultorio.model.Consulta;
import com.ygor.web_consultorio.model.Paciente;
import com.ygor.web_consultorio.repository.ConsultaRepository;
import com.ygor.web_consultorio.service.exception.DataBindingViolationException;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

	@Mock
	private ConsultaRepository repository;
	@Mock
	private PacienteService pacienteService;

	@InjectMocks
	private ConsultaService service;
	private ConsultaDTO dto;

	@BeforeEach
	void setup() {
		dto = new ConsultaDTO();
		dto.setDataHora(LocalDateTime.now());
	}

	@Test
	void devePermitirQuandoHorarioLivre() {
		when(repository.findByDataHoraAndStatusNot(any(), any()))
		.thenReturn(Optional.empty());
		assertDoesNotThrow(() -> service.validaHorario(dto, null));
	}

	@Test
	void deveLancarErroQuandoHorarioIndisponivel() {
		Consulta existente = new Consulta();
		existente.setId(1L);
		
		when(repository.findByDataHoraAndStatusNot(any(), any()))
		.thenReturn(Optional.of(existente));
		
		assertThrows(DataBindingViolationException.class, () -> service.validaHorario(dto, null));
	}

	@Test
	void devePermitirQuandoForAPropriaConsulta() {

		Consulta consultaExistente = new Consulta();
		consultaExistente.setId(1L);

		when(repository.findByDataHoraAndStatusNot(any(), any()))
		.thenReturn(Optional.of(consultaExistente));

		assertDoesNotThrow(() -> service.validaHorario(dto, 1L));
	}

	@Test
	void deveLancarErroQuandoConsultaNaoExiste() {
		
		when(repository.findById(999L))
		.thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundException.class, () -> service.findById(999L));

	}
	
	@Test
	void deveCancelarAoInvesDeDeletar() {
		Consulta consulta = new Consulta();
		consulta.setId(1L);
		consulta.setStatus(StatusConsulta.CONFIRMADA);
		
		when(repository.findById(1L)).thenReturn(Optional.of(consulta));
		
		service.delete(1L);

		assertEquals(StatusConsulta.CANCELADA, consulta.getStatus());
		verify(repository).save(consulta);
		verify(repository, never()).deleteById(any());
	}
	
	@Test
	void deveSetarStatusAgendadaAoCriar() {
	    dto.setPacienteId(1L);
	    dto.setStatus(StatusConsulta.REALIZADA);

	    Paciente paciente = new Paciente();
	    paciente.setId(1L);

	    when(repository.findByDataHoraAndStatusNot(any(), any()))
	            .thenReturn(Optional.empty());             
	    when(pacienteService.buscarEntidade(1L))
	            .thenReturn(paciente);                   
	    when(repository.save(any(Consulta.class)))
	            .thenAnswer(chamada -> chamada.getArgument(0)); 

	    ConsultaDTO salva = service.create(dto);

	    assertEquals(StatusConsulta.AGENDADA, salva.getStatus());
	}

	
	
}