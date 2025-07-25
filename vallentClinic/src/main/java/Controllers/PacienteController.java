
package Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.MySQL;
import Entities.Convenio;
import Entities.Paciente;
import Entities.Pessoa;
import Enums.EspecialidadesPsi;
import Enums.statusPaciente;
import Interface.PacienteDAO;
import Validators.ValidadorPaciente;
import Validators.ValidatorPessoa;

public class PacienteController implements PacienteDAO {

	@Override
	public Paciente callPacientes(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTA_PACIENTE(?)";
		Paciente paciente = null;
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ConvenioController cc = new ConvenioController();
			if (rs.next()) {

				paciente = new Paciente();
				paciente.setId(rs.getInt("ID"));
				paciente.setNome(rs.getString("NOME"));
				paciente.setCpf(rs.getString("CPF"));
				paciente.setRg(rs.getString("RG"));
				paciente.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				paciente.setTelefone(rs.getString("TELEFONE"));
				paciente.setEmail(rs.getString("EMAIL"));
				paciente.setEndereco(rs.getString("ENDERECO"));
				paciente.setNumero_prontuario(rs.getInt("NUMERO_PRONTUARIO"));
				paciente.setConvenio(cc.callConvenio(rs.getInt("ID_CONVENIO")));
				paciente.setObs(rs.getString("OBSERVACOES"));
				paciente.setStats(rs.getInt("ATIVO"));

			}
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar a query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return paciente;
	}

	@Override
	public List<Paciente> selectAll() {
		Connection conexao = MySQL.Conectar();
		String select = "SELECT * FROM PESSOA INNER JOIN PACIENTE ON PESSOA.ID = PACIENTE.ID_PESSOA";
		Paciente paci = null;
		ConvenioController cc = new ConvenioController();
		List<Paciente> lista = new ArrayList<>();
		try (PreparedStatement ps = conexao.prepareStatement(select)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				paci = new Paciente();
				paci.setId(rs.getInt("ID"));
				paci.setNome(rs.getString("NOME"));
				paci.setCpf(rs.getString("CPF"));
				paci.setRg(rs.getString("RG"));
				paci.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				paci.setTelefone(rs.getString("TELEFONE"));
				paci.setEmail(rs.getString("EMAIL"));
				paci.setEndereco(rs.getString("ENDERECO"));
				paci.setNumero_prontuario(rs.getInt("NUMERO_PRONTUARIO"));
				paci.setConvenio(cc.callConvenio(rs.getInt("ID_CONVENIO")));
				paci.setObs(rs.getString("OBSERVACOES"));
				paci.setStats(rs.getInt("ATIVO"));
				lista.add(paci);

			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
	}

	@Override
	public void insert(Paciente paciente) {
		Connection conexao = MySQL.Conectar();
		ValidatorPessoa.validar(paciente);
		ValidadorPaciente.validador(paciente);
		final String insertPessoa = "INSERT INTO PESSOA(NOME,CPF,RG,DATA_NASCIMENTO,TELEFONE,EMAIL,ENDERECO)VALUES(?,?,?,?,?,?,?)";

		// REALIZAR O INSERT PRIMEIRO EM PESSOA
		try (PreparedStatement psPessoa = conexao.prepareStatement(insertPessoa,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			psPessoa.setString(1, paciente.getNome());
			psPessoa.setString(2, paciente.getCpf());
			psPessoa.setString(3, paciente.getRg());
			psPessoa.setDate(4, Date.valueOf(paciente.getData_nascimento()));
			psPessoa.setString(5, paciente.getTelefone());
			psPessoa.setString(6, paciente.getEmail());
			psPessoa.setString(7, paciente.getEndereco());
			psPessoa.executeUpdate();
			ResultSet rs = psPessoa.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				paciente.setId(idGerado);

				final String insertPaciente = "INSERT INTO PACIENTE(ID_PESSOA,NUMERO_PRONTUARIO,ID_CONVENIO,OBSERVACOES,ATIVO)"
						+ "VALUES(?,?,?,?,?)";
				try (PreparedStatement ps = conexao.prepareStatement(insertPaciente)) {
					ps.setInt(1, idGerado);
					ps.setInt(2, paciente.getNumero_prontuario());
					ps.setInt(3, paciente.getConvenio().getId());
					ps.setString(4, paciente.getObs());
					ps.setInt(5, 1);
					ps.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException("Erro ao executar o insert em paciente" + e.getMessage());
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar o insert, valide" + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

	@Override
	public void update(Paciente paciente) {
		List<Paciente> lista = selectAll();
		for (Paciente paci : lista) {
			if (paciente.getId() == paci.getId()) {
				ValidatorPessoa.validar(paciente);
				ValidadorPaciente.validador(paciente);
				Connection conexao = null;
				final String updatePessoa = "UPDATE PESSOA SET NOME = ?, CPF = ?,RG = ?,DATA_NASCIMENTO = ?,TELEFONE = ?,EMAIL = ?,ENDERECO = ? WHERE ID = ?";
				final String updatePaciente = "UPDATE PACIENTE SET NUMERO_PRONTUARIO = ?,ID_CONVENIO = ?,OBSERVACOES = ? WHERE ID_PESSOA = ?";

				try {
					conexao = MySQL.Conectar();
					conexao.setAutoCommit(false);
					PreparedStatement psPessoa = conexao.prepareStatement(updatePessoa);
					psPessoa.setString(1, paciente.getNome());
					psPessoa.setString(2, paciente.getCpf());
					psPessoa.setString(3, paciente.getRg());
					psPessoa.setDate(4, Date.valueOf(paciente.getData_nascimento()));
					psPessoa.setString(5, paciente.getTelefone());
					psPessoa.setString(6, paciente.getEmail());
					psPessoa.setString(7, paciente.getEndereco());
					psPessoa.setInt(8, paciente.getId());
					psPessoa.executeUpdate();

					PreparedStatement psPaciente = conexao.prepareStatement(updatePaciente);

					psPaciente.setInt(1, paciente.getNumero_prontuario());
					psPaciente.setInt(2, paciente.getConvenio().getId());
					psPaciente.setString(3, paciente.getObs());
					psPaciente.setInt(4, paciente.getId());
					psPaciente.executeUpdate();
					conexao.commit();
				} catch (SQLException e) {
					if (conexao != null) {
						try {
							conexao.rollback();
						} catch (SQLException e2) {
							throw new RuntimeException("Erro ao executar " + e2.getMessage());
						}
					}
				}

			}else {
				throw new IllegalArgumentException("o ID inserido nao faz parte da lista de PACIENTES, validar se inseriu o id correto ");
			}

		}
	}

	@Override
	public void delete(int id) {
		List<Paciente> pacientes = selectAll();

		Paciente pacienteParaDeletar = null;
		for (Paciente paciente : pacientes) {
			if (paciente.getId() == id) {
				pacienteParaDeletar = paciente;
				break;
			}
		}

		if (pacienteParaDeletar == null) {
			throw new IllegalArgumentException("Erro: insira um ID válido de paciente.");
		}

		Connection conexao = MySQL.Conectar();
		final String delete = "DELETE FROM PESSOA WHERE ID = ?";
		try (PreparedStatement ps = conexao.prepareStatement(delete)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Paciente deletado com sucesso.");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar o delete, revise: " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
	}

}
