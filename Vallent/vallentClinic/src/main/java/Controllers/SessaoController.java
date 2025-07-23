package Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Connection.MySQL;
import Entities.Sessao;
import Enums.StatusSessao;
import Interface.SessaoDAO;
import Validators.ValidadorSessao;

public class SessaoController implements SessaoDAO {

	@Override
	public Sessao callSessao(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTA_SESSAO(?)";
		Sessao session = null;
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			PacienteController pc = new PacienteController();
			PsicologoController pcPsi = new PsicologoController();
			if (rs.next()) {
				session = new Sessao();
				session.setId(rs.getInt("ID"));
				session.setPaciente(pc.callPacientes(rs.getInt("ID_PACIENTE")));
				session.setPsicologo(pcPsi.callPsicologo(rs.getInt("ID_PSICOLOGO")));
				session.setData_hora(rs.getTimestamp("DATA_HORA").toLocalDateTime());
				session.setValor(rs.getDouble("VALOR"));
				session.setStatus(StatusSessao.fromDescricao(rs.getString("STATUS")));
				session.setObs(rs.getString("OBS"));

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar a query. " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return session;
	}

	@Override
	public List<Sessao> selectAll() {
		Connection conexao = MySQL.Conectar();
		String select = "SELECT * FROM SESSAO";
		Sessao session = null;
		List<Sessao> lista = new ArrayList<>();
		try (PreparedStatement ps = conexao.prepareStatement(select)) {
			ResultSet rs = ps.executeQuery();
			PacienteController pc = new PacienteController();
			PsicologoController pcPsi = new PsicologoController();
			while (rs.next()) {
				session = new Sessao();
				/*
				 * ID int(11) AI PK ID_PACIENTE int(11) ID_PSICOLOGO int(11) DATA_HORA date
				 * VALOR decimal(10,2) STATUS varchar(30) OBS varchar(100)
				 * 
				 */
				session.setId(rs.getInt("ID"));
				session.setPaciente(pc.callPacientes(rs.getInt("ID_PACIENTE")));
				session.setPsicologo(pcPsi.callPsicologo(rs.getInt("ID_PSICOLOGO")));
				session.setData_hora(rs.getTimestamp("DATA_HORA").toLocalDateTime());
				session.setValor(rs.getDouble("VALOR"));
				session.setStatus(StatusSessao.fromDescricao(rs.getString("STATUS")));
				session.setObs(rs.getString("OBS"));
				lista.add(session);

			}
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar a query, valide " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return lista;
	}

	@Override
	public void insert(Sessao session) {
		ValidadorSessao.validador(session);
		Connection conexao = MySQL.Conectar();

		final String insert = "INSERT INTO SESSAO(ID_PACIENTE,ID_PSICOLOGO,DATA_HORA,VALOR,STATUS,OBS)VALUES(?,?,?,?,?,?)";
		try (PreparedStatement ps = conexao.prepareStatement(insert)) {
			ps.setInt(1, session.getPaciente().getId());
			ps.setInt(2, session.getPsicologo().getId());
			ps.setTimestamp(3, Timestamp.valueOf(session.getData_hora()));
			ps.setDouble(4, session.getValor());
			ps.setString(5, session.getStatus().toString());
			ps.setString(6, session.getObs());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar o insert, validar " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

	@Override
	public void update(Sessao convenio) {
		ValidadorSessao.validador(convenio);
		Connection conexao = MySQL.Conectar();
		int contadorDeLinhas = 0;
		final String update = "UPDATE SESSAO SET ID_PACIENTE = ?,ID_PSICOLOGO = ?,DATA_HORA = ?,VALOR = ?,STATUS = ?,OBS = ? WHERE ID = ?";
		try (PreparedStatement ps = conexao.prepareStatement(update)) {
			ps.setInt(1, convenio.getPaciente().getId());
			ps.setInt(2, convenio.getPsicologo().getId());
			ps.setTimestamp(3, Timestamp.valueOf(convenio.getData_hora()));
			ps.setDouble(4, convenio.getValor());
			ps.setString(5, convenio.getStatus().toString());
			ps.setString(6, convenio.getObs());
			ps.setInt(7, convenio.getId());
			contadorDeLinhas = ps.executeUpdate();
			if (contadorDeLinhas == 0) {
				throw new RuntimeException("nenhuma linha alterada, valide");
			}
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar o insert, validar " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

	@Override
	public void delete(int id) {
		Connection conexao = MySQL.Conectar();
		final String delete = "DELETE FROM SESSAO WHERE ID = ?";
		try (PreparedStatement ps = conexao.prepareStatement(delete)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar o delete, valide" + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
	}

}
