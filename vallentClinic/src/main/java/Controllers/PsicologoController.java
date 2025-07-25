package Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import Connection.MySQL;
import Entities.Pessoa;
import Entities.Psicologo;
import Enums.EspecialidadesPsi;
import Interface.PsicologoDAO;
import Validators.ValidadorPsicologo;
import Validators.ValidatorPessoa;

public class PsicologoController implements PsicologoDAO {

	@Override
	public Psicologo callPsicologo(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTA_PSICOLOGO(?)";
		Psicologo psi = null;
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				psi = new Psicologo();
				psi.setId(rs.getInt("ID"));
				psi.setNome(rs.getString("NOME"));
				psi.setCpf(rs.getString("CPF"));
				psi.setRg(rs.getString("RG"));
				psi.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				psi.setTelefone(rs.getString("TELEFONE"));
				psi.setEmail(rs.getString("EMAIL"));
				psi.setEndereco(rs.getString("ENDERECO"));
				psi.setId(rs.getInt("ID"));
				psi.setCrp(rs.getString("CRP"));
				psi.setEspecialidade(EspecialidadesPsi.fromDescricao(rs.getString("ESPECIALIDADES")));
				psi.setHorario(rs.getDate("HORARIO_ATENDIMENTO").toLocalDate());

			}

		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar a query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return psi;
	}

	@Override
	public List<Psicologo> selectAll() {
		Connection conexao = MySQL.Conectar();
		String selectAll = "SELECT * FROM PESSOA INNER JOIN PSICOLOGO ON PESSOA.ID = PSICOLOGO.ID_PESSOA  ";
		List<Psicologo> lista = new ArrayList<>();
		Psicologo psi = null;
		try (PreparedStatement ps = conexao.prepareStatement(selectAll)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				psi = new Psicologo();
				psi.setId(rs.getInt("ID"));
				psi.setNome(rs.getString("NOME"));
				psi.setCpf(rs.getString("CPF"));
				psi.setRg(rs.getString("RG"));
				psi.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				psi.setTelefone(rs.getString("TELEFONE"));
				psi.setEmail(rs.getString("EMAIL"));
				psi.setEndereco(rs.getString("ENDERECO"));
				psi.setId(rs.getInt("ID"));
				psi.setCrp(rs.getString("CRP"));
				psi.setEspecialidade(EspecialidadesPsi.fromDescricao(rs.getString("ESPECIALIDADES")));
				psi.setHorario(rs.getDate("HORARIO_ATENDIMENTO").toLocalDate());
				lista.add(psi);

			}
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar a query, revise: " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return lista;
	}

	@Override
	public void insert(Psicologo psicologo) {
		Connection conexao = MySQL.Conectar();
		ValidatorPessoa.validar(psicologo);
		ValidadorPsicologo.validador(psicologo);
		final String insertPessoa = "INSERT INTO PESSOA(NOME,CPF,RG,DATA_NASCIMENTO,TELEFONE,EMAIL,ENDERECO)VALUES(?,?,?,?,?,?,?);";

		try (PreparedStatement ps = conexao.prepareStatement(insertPessoa, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, psicologo.getNome());
			ps.setString(2, psicologo.getCpf());
			ps.setString(3, psicologo.getRg());
			ps.setDate(4, Date.valueOf(psicologo.getData_nascimento()));
			ps.setString(5, psicologo.getTelefone());
			ps.setString(6, psicologo.getEmail());
			ps.setString(7, psicologo.getEndereco());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int id = rs.getInt(1);
				psicologo.setId(id);
				final String insertPsicologo = "INSERT INTO PSICOLOGO(ID_PESSOA,CRP,ESPECIALIDADES,HORARIO_ATENDIMENTO)VALUES(?,?,?,?)";
				try (PreparedStatement ps2 = conexao.prepareStatement(insertPsicologo)) {
					ps2.setInt(1, id);
					ps2.setString(2, psicologo.getCrp());
					ps2.setString(3, psicologo.getEspecialidade().toString());
					ps2.setDate(4, Date.valueOf(psicologo.getHorario()));
					ps2.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException("Erro ao executar o Insert em psicologo, revise " + e.getMessage());
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar o insert, validar: " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
	}

	@Override
	public void update(Psicologo psi) {
		boolean found = false;
		List<Psicologo> lista = selectAll();
		for (Psicologo psi2 : lista) {
			if (psi.getId() == psi2.getId()) {
				found = true;
				break;
			}

		}
		if (found == false) {
			throw new IllegalArgumentException(
					"ocorreu um erro, o id inserido nao faz parte da lista de psicologos, valide o ID ");
		} else {
			Connection conexao = null;
			ValidatorPessoa.validar(psi);
			ValidadorPsicologo.validador(psi);

			final String updatePessoa = "UPDATE PESSOA SET NOME = ?, CPF = ?,RG = ?, DATA_NASCIMENTO = ?,TELEFONE = ?,EMAIL = ?,ENDERECO = ? WHERE ID = ?";
			final String updatePsicologo = "UPDATE PSICOLOGO SET CRP = ?,ESPECIALIDADES = ?,HORARIO_ATENDIMENTO = ? WHERE ID_PESSOA = ?";

			try {
				conexao = MySQL.Conectar();
				conexao.setAutoCommit(false);
				PreparedStatement ps = conexao.prepareStatement(updatePessoa);
				ps.setString(1, psi.getNome());
				ps.setString(2, psi.getCpf());
				ps.setString(3, psi.getRg());
				ps.setDate(4, Date.valueOf(psi.getData_nascimento()));
				ps.setString(5, psi.getTelefone());
				ps.setString(6, psi.getEmail());
				ps.setString(7, psi.getEndereco());
				ps.setInt(8, psi.getId());
				ps.executeUpdate();

				PreparedStatement psPsi = conexao.prepareStatement(updatePsicologo);
				psPsi.setString(1, psi.getCrp());
				psPsi.setString(2, psi.getEspecialidade().toString());
				psPsi.setDate(3, Date.valueOf(psi.getHorario()));
				psPsi.setInt(4, psi.getId());
				psPsi.executeUpdate();
				
				conexao.commit();
			} catch (SQLException e) {
				if(conexao != null) {
					try {
						conexao.rollback();
					}catch(SQLException ex) {
						throw new RuntimeException("erro ao executar o rollback " +ex.getMessage());
					}
				}
			}finally {
				MySQL.Desconectar(conexao);
			}

		}
	}

	@Override
	public void delete(int id) {
		boolean found = false;
		List<Psicologo> lista = selectAll();
		for (Psicologo psi : lista) {
			if (id == psi.getId()) {
				found = true;
				break;
			}
		}
		if(found == false) {
			throw new IllegalArgumentException("erro, nao existe psicologo com ID informado, valide");
			
		}else {
			Connection conexao = MySQL.Conectar();
			final String delete = "DELETE FROM PESSOA WHERE ID = ?";
			try (PreparedStatement ps = conexao.prepareStatement(delete)) {
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("erro ao executar o delete, revise " + e.getMessage());
			} finally {
				MySQL.Desconectar(conexao);
			}

		}

	}

}
