package com.ygor.web_consultorio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ygor.web_consultorio.dto.ConsultaTesteDTO;
import com.ygor.web_consultorio.model.Consulta;
import com.ygor.web_consultorio.model.ConsultaTeste;
import com.ygor.web_consultorio.model.TestePsicologico;
import com.ygor.web_consultorio.repository.ConsultaRepository;
import com.ygor.web_consultorio.repository.ConsultaTesteRepository;
import com.ygor.web_consultorio.repository.TestePsicologicoRepository;
import com.ygor.web_consultorio.service.exception.DataBindingViolationException;
import com.ygor.web_consultorio.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultaTesteService {

	private final ConsultaTesteRepository repository;
	private final TestePsicologicoRepository testePsicologicoRepository;
	private final ConsultaRepository consultaRepository;

	public ConsultaTesteDTO create(Long consultaId, ConsultaTesteDTO objDTO) {
		Consulta consulta = buscarConsulta(consultaId);
		TestePsicologico teste = buscarTeste(objDTO.getTesteId());

		validaEstoque(teste, objDTO.getQtd());
		teste.setQtdEstoque(teste.getQtdEstoque() - objDTO.getQtd());
		testePsicologicoRepository.save(teste);

		verificaEstoqueBaixo(teste);

		ConsultaTeste ct = new ConsultaTeste();
		ct.setConsulta(consulta);
		ct.setTeste(teste);
		ct.setQtd(objDTO.getQtd());
		ct.setCustoUnitario(teste.getCustoUnitario());

		ct = repository.save(ct);
		return new ConsultaTesteDTO(ct);
	}

	@Transactional(readOnly = true)
	public List<ConsultaTesteDTO> findByConsulta(Long consultaId) {
		buscarConsulta(consultaId);
		return repository.findByConsultaId(consultaId).stream().map(ConsultaTesteDTO::new).toList();
	}

	private void validaEstoque(TestePsicologico teste, Integer qtd) {
		if (teste.getQtdEstoque() < qtd) {
			throw new DataBindingViolationException("Estoque insuficiente para o teste: " + teste.getNome());
		}
	}

	private void verificaEstoqueBaixo(TestePsicologico teste) {
		if (teste.getQtdEstoque() <= teste.getEstoqueMinimo()) {
			log.warn("Estoque baixo para o teste '{}': {} unidades restantes (mínimo: {})", teste.getNome(),
					teste.getQtdEstoque(), teste.getEstoqueMinimo());
		}
	}

	private TestePsicologico buscarTeste(Long testeId) {
		return testePsicologicoRepository.findById(testeId)
				.orElseThrow(() -> new ObjectNotFoundException("Teste não encontrado!"));
	}

	private Consulta buscarConsulta(Long consultaId) {
		return consultaRepository.findById(consultaId)
				.orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrada!"));
	}
}
