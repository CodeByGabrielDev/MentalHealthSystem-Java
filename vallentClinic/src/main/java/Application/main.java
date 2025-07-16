package Application;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Controllers.ConvenioController;
import Controllers.PacienteController;
import Entities.Convenio;
import Entities.Paciente;
import Entities.Pessoa;
import Enums.statusPaciente;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		PacienteController pc = new PacienteController();
		pc.delete(id);
		
	}

	public static LocalDate converter(String date) {
		LocalDate dateConvert = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return dateConvert;

	}
}
