package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.MySQL;
import Entities.Convenio;
import Interface.ConvenioDAO;
import Validators.ValidadorConvenio;

public class ConvenioController implements ConvenioDAO {

	@Override
	public Convenio callConvenio(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTA_CONVENIO(?)";
		Convenio convenio = null;

		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				convenio = new Convenio();
				convenio.setId(rs.getInt("id"));
				convenio.setNome(rs.getString("nome"));
				convenio.setCnpj(rs.getString("cnpj"));
				convenio.setTelefone(rs.getString("telefone"));
				convenio.setEmail(rs.getString("email"));

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar a procedure " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return convenio;
	}

	@Override
	public List<Convenio> selectAll() {
		Connection conexao = MySQL.Conectar();
		String select = "SELECT * FROM convenio";
		Convenio convenio = null;
		List<Convenio> list = new ArrayList<>();
		try (PreparedStatement ps = conexao.prepareStatement(select)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				convenio = new Convenio();
				convenio.setId(rs.getInt("id"));
				convenio.setNome(rs.getString("nome"));
				convenio.setCnpj(rs.getString("cnpj"));
				convenio.setTelefone(rs.getString("telefone"));
				convenio.setEmail(rs.getString("email"));
				list.add(convenio);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar o SELECT, verifique a query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return list;
	}

	@Override
	public void insert(Convenio convenio) {
		Connection conexao = MySQL.Conectar();
		ValidadorConvenio.validador(convenio);
		final String insert = "INSERT INTO convenio(nome,cnpj,telefone,email)VALUES(?,?,?,?)";
		try (PreparedStatement ps = conexao.prepareStatement(insert)) {

			ps.setString(1, convenio.getNome());
			ps.setString(2, convenio.getCnpj());
			ps.setString(3, convenio.getTelefone());
			ps.setString(4, convenio.getEmail());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(
					"Erro ao executar o INSERT do objeto CONVENIO, revise os dados " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

	@Override
	public void update(Convenio convenio) {
		Connection conexao = MySQL.Conectar();
		final String update = "UPDATE CONVENIO SET NOME = ?, CNPJ = ?, TELEFONE = ?, EMAIL = ? WHERE ID = ?";
		try (PreparedStatement ps = conexao.prepareStatement(update)) {
			ps.setString(1, convenio.getNome());
			ps.setString(2, convenio.getCnpj());
			ps.setString(3, convenio.getTelefone());
			ps.setString(4, convenio.getEmail());
			ps.setInt(5, convenio.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Valide o update, ocorreu um erro " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

	@Override
	public void delete(int id) {
		Connection conexao = MySQL.Conectar();
		final String delete = "DELETE FROM CONVENIO WHERE ID = ?";
		try (PreparedStatement ps = conexao.prepareStatement(delete)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar o DELETE na tabela CONVENIO " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

}
