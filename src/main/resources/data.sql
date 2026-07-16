INSERT INTO paciente (nome, email, endereco, telefone, cpf, data_nascimento, convenio, profissao, nome_pai, nome_mae, sexo) VALUES
('Mariana Alves Ferreira', 'mariana.ferreira@gmail.com', 'Rua das Acácias, 245 - Centro, Santa Rita do Sapucaí - MG', '35991234567', '089.833.390-39', '1990-03-12', 'Unimed', 'Arquiteta', 'Carlos Alberto Ferreira', 'Sandra Regina Alves', 'FEMININO'),
('João Pedro Machado', 'joao.machado@gmail.com', 'Av. Cel. Joaquim Neto, 512 - Santana, Santa Rita do Sapucaí - MG', '35998765432', '123.456.789-09', '1985-07-25', 'Bradesco Saúde', 'Engenheiro', 'Antônio Machado', 'Rita de Cássia', 'MASCULINO'),
('Beatriz Souza Lima', 'bia.lima@gmail.com', 'Rua São Paulo, 78 - Centro, Pouso Alegre - MG', '35997654321', '987.654.321-00', '1998-11-05', 'Particular', 'Estudante', 'Marcos Lima', 'Fernanda Souza', 'FEMININO'),
('Ricardo Nunes Teixeira', 'ricardo.teixeira@gmail.com', 'Rua das Flores, 330 - Jardim América, Itajubá - MG', '35991112233', '456.789.123-72', '1979-02-18', 'Amil', 'Advogado', 'José Teixeira', 'Lúcia Nunes', 'MASCULINO');


INSERT INTO consulta (data_hora, duracao_minutos, valor, status, tipo, observacoes, paciente_id) VALUES
('2026-07-20 09:00:00', 50, 180.00, 'AGENDADA', 'PRIMEIRA_CONSULTA', 'Encaminhada pela clínica parceira. Queixa: ansiedade e insônia.', 1),
('2026-07-27 09:00:00', 50, 150.00, 'CONFIRMADA', 'RETORNO', 'Retorno após primeira sessão. Evolução positiva.', 1),
('2026-07-21 14:00:00', 50, 150.00, 'REALIZADA', 'SESSAO', 'Sessão de acompanhamento. Trabalhado manejo de estresse.', 2),
('2026-07-22 10:30:00', 50, 200.00, 'AGENDADA', 'AVALIACAO', 'Início do processo de avaliação neuropsicológica.', 3),
('2026-07-23 16:00:00', 50, 180.00, 'FALTOU', 'PRIMEIRA_CONSULTA', 'Paciente não compareceu, sem aviso.', 4);
