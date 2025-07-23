package Application;

import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Controllers.ConvenioController;
import Controllers.MovimentacaoController;
import Controllers.PacienteController;
import Controllers.PsicologoController;
import Controllers.SessaoController;
import Entities.Convenio;
import Entities.Movimentacao;
import Entities.Paciente;
import Entities.Pessoa;
import Entities.Psicologo;
import Entities.Sessao;
import Enums.EspecialidadesPsi;
import Enums.FormaPgto;
import Enums.StatusSessao;
import Enums.TipoMovimentacao;
import Enums.statusPaciente;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PacienteController pc = new PacienteController();
		ConvenioController cc = new ConvenioController();
		PsicologoController psiC = new PsicologoController();
		SessaoController session = new SessaoController();
		MovimentacaoController movi = new MovimentacaoController();

		/*
		 * private int id;
	private LocalDateTime data_movimentacao;
	private double valor;
	private Paciente paciente;
	private String descricao;
	private TipoMovimentacao tipo;
	private FormaPgto forma_pgto;
	private int faturada;
	private Psicologo psicologo;
	private Convenio convenio;
	private int estornada;
		 */
		System.out.println("valor da consulta");
		double valor = sc.nextDouble();
		System.out.println("identificador do paciente ao cadastrar");
		int id = sc.nextInt();
		Paciente call= pc.callPacientes(id);
		System.out.println("descricao da consulta");
		String desc = sc.next();
		TipoMovimentacao tipo = TipoMovimentacao.CONSULTA_PARTICULAR;
		FormaPgto forma = FormaPgto.DINHEIRO;
		int faturada = 0;
		System.out.println("identificador do psicologo ao cadastrar");
		int idPsi = sc.nextInt();
		Psicologo psi = psiC.callPsicologo(idPsi);
		System.out.println("identificador do convenio ao cadastrar");
		int idC = sc.nextInt();
		Convenio convenio = cc.callConvenio(idC);
		Movimentacao mov = new Movimentacao(LocalDateTime.now(),valor, call, desc, tipo, forma, faturada, psi, convenio);
		movi.insert(mov);
		
		
	}

	public static LocalDate converter(String date) {
		LocalDate dateConvert = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return dateConvert;

	}

	public static LocalDateTime converterTime(String date) {
		LocalDateTime dateConvert = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return dateConvert;
	}

}
