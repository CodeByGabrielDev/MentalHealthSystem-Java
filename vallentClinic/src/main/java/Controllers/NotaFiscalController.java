package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Connection.MySQL;

import Entities.NotaFiscal;

import Interface.NotaFiscalDAO;

public class NotaFiscalController implements NotaFiscalDAO {

	@Override
	public NotaFiscal callNota(int numero_nota) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTA_NOTA_FISCAL(?)";
		NotaFiscal nota = null;
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, numero_nota);
			ResultSet rs = ps.executeQuery();
			MovimentacaoController mc = new MovimentacaoController();
			PacienteController pc = new PacienteController();
			if (rs.next()) {
				nota = new NotaFiscal();
				nota.setNumero_nota(rs.getInt("NUMERO_NOTA"));
				nota.setCfop(rs.getString("CFOP"));
				nota.setId_movi(mc.callMovimentacao(rs.getInt("ID_MOVIMENTACAO")));
				nota.setEmissao(rs.getTimestamp("EMISSAO").toLocalDateTime());
				nota.setDesc(rs.getString("DESCRICAO"));
				nota.setPaciente(pc.callPacientes(rs.getInt("ID_PACIENTE")));
				nota.setCnpjEmitente(rs.getString("CNPJ_EMITENTE"));

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar a Query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return nota;
	}

	@Override
	public List<NotaFiscal> selectAll() {
		Connection conexao = MySQL.Conectar();
		List<NotaFiscal> lista = new ArrayList<>();
		String call = "SELECT * FROM NOTA_FISCAL";
		NotaFiscal nota = null;
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ResultSet rs = ps.executeQuery();
			MovimentacaoController mc = new MovimentacaoController();
			PacienteController pc = new PacienteController();
			while (rs.next()) {
				nota = new NotaFiscal();
				nota.setNumero_nota(rs.getInt("NUMERO_NOTA"));
				nota.setCfop(rs.getString("CFOP"));
				nota.setId_movi(mc.callMovimentacao(rs.getInt("ID_MOVIMENTACAO")));
				nota.setEmissao(rs.getTimestamp("EMISSAO").toLocalDateTime());
				nota.setDesc(rs.getString("DESCRICAO"));
				nota.setPaciente(pc.callPacientes(rs.getInt("ID_PACIENTE")));
				nota.setCnpjEmitente(rs.getString("CNPJ_EMITENTE"));
				lista.add(nota);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar a Query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return lista;
	}

	@Override
	public NotaFiscal selectId(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "SELECT * FROM NOTA_FISCAL WHERE ID_MOVIMENTACAO = ?";
		NotaFiscal nota = null;
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			MovimentacaoController mc = new MovimentacaoController();
			PacienteController pc = new PacienteController();
			if (rs.next()) {
				nota = new NotaFiscal();
				nota.setNumero_nota(rs.getInt("NUMERO_NOTA"));
				nota.setCfop(rs.getString("CFOP"));
				nota.setId_movi(mc.callMovimentacao(rs.getInt("ID_MOVIMENTACAO")));
				nota.setEmissao(rs.getTimestamp("EMISSAO").toLocalDateTime());
				nota.setDesc(rs.getString("DESCRICAO"));
				nota.setPaciente(pc.callPacientes(rs.getInt("ID_PACIENTE")));
				nota.setCnpjEmitente(rs.getString("CNPJ_EMITENTE"));

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar a Query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return nota;
	}



}
