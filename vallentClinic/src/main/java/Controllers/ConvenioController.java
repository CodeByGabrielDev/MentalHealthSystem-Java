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

public class ConvenioController implements ConvenioDAO {

	@Override
	public Convenio callConvenio(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTACONVENIOS(?)";
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
		String select = "SELECT * FROM CONVENIO";
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
		
	}

	@Override
	public void update(Convenio convenio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
