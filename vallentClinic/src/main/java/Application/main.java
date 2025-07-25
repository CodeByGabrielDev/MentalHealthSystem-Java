package Application;

import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
		MovimentacaoController mv = new MovimentacaoController();
		PsicologoController psiC = new PsicologoController();
		PacienteController pc = new PacienteController();
		ConvenioController cc = new ConvenioController();
		int id = sc.nextInt();
		mv.cancelamentoDeMovimentacao(id);
		
		
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
