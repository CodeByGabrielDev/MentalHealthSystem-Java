package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Connection.MySQL;
import Entities.Movimentacao;
import Entities.NotaFiscal;
import Entities.Paciente;
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
				mov.setForma_pgto(FormaPgto.fromDescricao(rs.getString("FORMA_PGTO")));
				mov.setFaturada(rs.getInt("FATURADA"));
				mov.setPsicologo(pcPsi.callPsicologo(rs.getInt("ID_PSICOLOGO")));
				mov.setConvenio(cc.callConvenio(rs.getInt("ID_CONVENIO")));
				mov.setEstornada(rs.getInt("ESTORNADA"));
				mov.setCancelada(rs.getInt("CANCELADA"));
				
				Timestamp tsCancelamento = rs.getTimestamp("DATA_CANCELAMENTO");
				if (tsCancelamento != null) {
					mov.setData_cancelamento(tsCancelamento.toLocalDateTime());
				} else {
					mov.setData_cancelamento(null);
				}

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
				mov.setForma_pgto(FormaPgto.fromDescricao(rs.getString("FORMA_PGTO")));
				mov.setFaturada(rs.getInt("FATURADA"));
				mov.setPsicologo(pcPsi.callPsicologo(rs.getInt("ID_PSICOLOGO")));
				mov.setConvenio(cc.callConvenio(rs.getInt("ID_CONVENIO")));
				mov.setEstornada(rs.getInt("ESTORNADA"));
				mov.setCancelada(rs.getInt("CANCELADA"));
				mov.setData_cancelamento(rs.getTimestamp("DATA_CANCELAMENTO").toLocalDateTime());
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
		final String insertMov = "INSERT INTO MOVIMENTACAO(DATA_MOVIMENTACAO,VALOR,ID_CLIENTE,DESCRICAO,TIPO,FORMA_PGTO,FATURADA,ID_PSICOLOGO,ID_CONVENIO,ESTORNADA,CANCELADA,DATA_CANCELAMENTO)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		final String insertNotaFiscal = "INSERT INTO NOTA_FISCAL(ID_MOVIMENTACAO,CFOP,EMISSAO,VALOR_NOTA,DESCRICAO,ID_PACIENTE,CNPJ_EMITENTE,CANCELADA,DATA_CANCELAMENTO)VALUES(?,?,?,?,?,?,?,?,?)";
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
			ps.setInt(11, mov.getCancelada());
			ps.setNull(12, java.sql.Types.TIMESTAMP);
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
				switch (mov.getTipo()) {
				case CONSULTA_PARTICULAR:
					notaF.setCfop("5933");
					break;
				case CONSULTA_CONVENIO:
					notaF.setCfop("5933");
					break;

				case AVALIACAO_PSICOLOGICA:
					notaF.setCfop("5933");
					break;

				case REEMBOLSO:
					notaF.setCfop("1202");
					break;

				case VENDA_PRODUTO:
					notaF.setCfop("5102");
					break;

				case ESTORNO:
					notaF.setCfop("1202");
					break;
				default:
					throw new IllegalArgumentException("Tipo invalido.");
				}
				psNota.setString(2, notaF.getCfop());
				psNota.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				psNota.setDouble(4, notaF.getValor_nota());
				psNota.setString(5, notaF.getDesc());
				psNota.setInt(6, notaF.getPaciente().getId());
				psNota.setString(7, notaF.getCnpjEmitente());
				psNota.setInt(8, notaF.getCancelada());
				psNota.setNull(9, java.sql.Types.TIMESTAMP);
				psNota.executeUpdate();
			}
			conexao.commit();

		} catch (SQLException e) {
			throw new RuntimeException("erro ao executar" + e.getMessage());
		} finally {
			MySQL.Desconectar(conexao);
		}
	}

	@Override
	public void cancelamentoDeMovimentacao(int id) {
		boolean autorizadoCancelar = false;
		boolean found = false;
		boolean foudNota = false;
		Movimentacao mov = callMovimentacao(id);
		NotaFiscalController nf = new NotaFiscalController();
		NotaFiscal busca = nf.selectId(id);

		if (mov != null) {
			found = true;

			long validador = ChronoUnit.MINUTES.between(mov.getData_movimentacao(), LocalDateTime.now());
			if (validador > 30) {
				throw new RuntimeException("Não é possivel realizar o cancelamento da venda após 30 minutos. ");
			} else {
				autorizadoCancelar = true;
				Connection conexao = MySQL.Conectar();
				final String update = "UPDATE MOVIMENTACAO SET CANCELADA = 1, ESTORNADA = 1, DATA_CANCELAMENTO = NOW() WHERE ID = ?";
				final String updateNota = "UPDATE NOTA_FISCAL SET CANCELADA = 1,DATA_CANCELAMENTO = NOW() WHERE ID_MOVIMENTACAO = ?";
				
				try {
					conexao.setAutoCommit(false); // INÍCIO DA TRANSAÇÃO

					try (PreparedStatement ps = conexao.prepareStatement(update)) {
						ps.setInt(1, id);
						int contador = ps.executeUpdate();
						if (contador == 0) {
							throw new IllegalArgumentException("nenhum id foi encontrado. ");
						}
					}

					if (busca.getId_movi().getId() == id) {
						foudNota = true;
						try (PreparedStatement psNota = conexao.prepareStatement(updateNota)) {
							long validador2 = ChronoUnit.HOURS.between(busca.getEmissao(), LocalDateTime.now());
							if(validador2 >24) {
								throw new RuntimeException("não é possivel realizar o cancelamento da nota após 24 HORAS autorizada pela sefaz");
							}else {
								psNota.setInt(1, id);
								psNota.executeUpdate();
							}
						}
					} else {
						throw new RuntimeException("não foi encontrado nota fiscal com esse id de movimentação.");
					}

					conexao.commit(); // COMMITA TUDO
				} catch (SQLException e) {
					try {
						conexao.rollback(); // VOLTA TUDO
					} catch (SQLException ex) {
						throw new RuntimeException("Erro ao tentar rollback: " + ex.getMessage());
					}
					throw new RuntimeException("erro ao executar o update." + e.getMessage());
				} finally {
					MySQL.Desconectar(conexao);
				}
			}
		}
	}

}
