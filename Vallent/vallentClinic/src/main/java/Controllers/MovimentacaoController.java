package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Connection.MySQL;
import Entities.Movimentacao;
import Entities.NotaFiscal;
import Enums.FormaPgto;
import Enums.TipoMovimentacao;
import Interface.MovimentacaoDAO;
import Validators.ValidadorMovimentacao;

public class MovimentacaoController implements MovimentacaoDAO {

	@Override
	public Movimentacao callMovimentacao(int id) {
		Connection conexao = MySQL.Conectar();
		String call = "CALL CONSULTA_MOVIMENTACAO(?)";
		Movimentacao mov = null;
		PacienteController pc = new PacienteController();
		PsicologoController pcPsi = new PsicologoController();
		ConvenioController cc = new ConvenioController();
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				mov = new Movimentacao();
				mov.setId(rs.getInt("ID"));
				mov.setData_movimentacao(rs.getTimestamp("DATA_MOVIMENTACAO").toLocalDateTime());
				mov.setValor(rs.getDouble("VALOR"));
				mov.setPaciente(pc.callPacientes(rs.getInt("ID_CLIENTE")));
				mov.setDescricao(rs.getString("DESCRICAO"));
				mov.setTipo(TipoMovimentacao.fromDescricao(rs.getString("TIPO")));
				mov.setForma_pgto(FormaPgto.fromDescricao("FORMA_PGTO"));
				mov.setFaturada(rs.getInt("FATURADA"));
				mov.setPsicologo(pcPsi.callPsicologo(rs.getInt("ID_PSICOLOGO")));
				mov.setConvenio(cc.callConvenio(rs.getInt("ID_CONVENIO")));
				mov.setEstornada(rs.getInt("ESTORNADA"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar a query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return mov;
	}

	@Override
	public List<Movimentacao> selectAll() {
		Connection conexao = MySQL.Conectar();
		String call = "SELECT * FROM MOVIMENTACAO";
		List<Movimentacao> lista = new ArrayList<>();
		Movimentacao mov = null;
		PacienteController pc = new PacienteController();
		PsicologoController pcPsi = new PsicologoController();
		ConvenioController cc = new ConvenioController();
		try (PreparedStatement ps = conexao.prepareStatement(call)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mov = new Movimentacao();
				mov.setId(rs.getInt("ID"));
				mov.setData_movimentacao(rs.getTimestamp("DATA_MOVIMENTACAO").toLocalDateTime());
				mov.setValor(rs.getDouble("VALOR"));
				mov.setPaciente(pc.callPacientes(rs.getInt("ID_CLIENTE")));
				mov.setDescricao(rs.getString("DESCRICAO"));
				mov.setTipo(TipoMovimentacao.fromDescricao(rs.getString("TIPO")));
				mov.setForma_pgto(FormaPgto.fromDescricao("FORMA_PGTO"));
				mov.setFaturada(rs.getInt("FATURADA"));
				mov.setPsicologo(pcPsi.callPsicologo(rs.getInt("ID_PSICOLOGO")));
				mov.setConvenio(cc.callConvenio(rs.getInt("ID_CONVENIO")));
				mov.setEstornada(rs.getInt("ESTORNADA"));
				lista.add(mov);
			}

		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar a query " + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
		return lista;
	}

	@Override
	public void insert(Movimentacao mov) {
		Connection conexao = null;
		final String insertMov = "INSERT INTO MOVIMENTACAO(DATA_MOVIMENTACAO,VALOR,ID_CLIENTE,DESCRICAO,TIPO,FORMA_PGTO,FATURADA,ID_PSICOLOGO,ID_CONVENIO,ESTORNADA)VALUES(?,?,?,?,?,?,?,?,?,?)";
		final String insertNotaFiscal = "INSERT INTO NOTA_FISCAL(ID_MOVIMENTACAO,EMISSAO,VALOR_NOTA,DESCRICAO,ID_PACIENTE,CNPJ_EMITENTE)VALUES(?,?,?,?,?,?)";
		ValidadorMovimentacao.validador(mov);
		try {
			conexao = MySQL.Conectar();
			conexao.setAutoCommit(false);
			PacienteController pc = new PacienteController();
			PreparedStatement ps = conexao.prepareStatement(insertMov, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, Timestamp.valueOf(mov.getData_movimentacao()));
			ps.setDouble(2, mov.getValor());
			ps.setInt(3, mov.getPaciente().getId());
			ps.setString(4, mov.getDescricao());
			ps.setString(5, mov.getTipo().toString());
			ps.setString(6, mov.getForma_pgto().toString());
			ps.setInt(7, mov.getFaturada());
			ps.setInt(8, mov.getPsicologo().getId());
			ps.setInt(9, mov.getConvenio().getId());
			ps.setInt(10, mov.getEstornada());
			int id2 = ps.executeUpdate();
			System.out.println(id2);
			ResultSet rs = ps.getGeneratedKeys();
			NotaFiscal notaF = null;
			if (rs.next()) {
				notaF = new NotaFiscal();
				int id = rs.getInt(1);
				PreparedStatement psNota = conexao.prepareStatement(insertNotaFiscal);
				notaF.setDesc(mov.getDescricao());
				notaF.setValor_nota(mov.getValor());
				notaF.setPaciente(mov.getPaciente());
				psNota.setInt(1, id);
				psNota.setTimestamp(2, Timestamp.valueOf(notaF.getEmissao().now()));
				psNota.setDouble(3, notaF.getValor_nota());
				psNota.setString(4, notaF.getDesc());
				psNota.setInt(5, notaF.getPaciente().getId());
				psNota.setString(6, notaF.getCnpjEmitente());
				psNota.executeUpdate();
			}
			conexao.commit();
			

		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar" +e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
	}

	@Override
	public void update(Movimentacao mov) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		List<Movimentacao> lista = selectAll();
		boolean found = false;
		for (Movimentacao mov : lista) {
			if (mov.getId() == id) {
				found = true;
				break;
			}
		}
		if (found == false) {
			throw new IllegalArgumentException("nao foi encontrado nenhum id na lista de movimentacoes para deleção");
		} else {
			Connection conexao = MySQL.Conectar();

		}

	}

}
