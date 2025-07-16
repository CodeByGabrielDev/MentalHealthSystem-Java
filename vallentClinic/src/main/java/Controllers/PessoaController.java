package Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import Connection.MySQL;
import Entities.Pessoa;
import Interface.PessoaDAO;

public class PessoaController implements PessoaDAO {

	

	@Override
	public void delete(int id) {
		Connection conexao = MySQL.Conectar();
		final String delete = "DELETE FROM PESSOA WHERE ID = ?";
		try (PreparedStatement ps = conexao.prepareStatement(delete)) {
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao rodar o Delete no objeto Pessoa, validar. " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}

	}

}
