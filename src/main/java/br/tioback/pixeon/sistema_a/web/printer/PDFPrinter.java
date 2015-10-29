package br.tioback.pixeon.sistema_a.web.printer;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.web.ReturnType;

public class PDFPrinter extends ResponsePrinter {

	@Override
	protected ReturnType getReturnType() {
		return ReturnType.PDF;
	}

	@Override
	public void printPaciente(Paciente paciente, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPacientes(Collection<Paciente> pacientes,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printClinica(Clinica clinica, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printClinicas(Collection<Clinica> clinicas,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printExame(Exame exame, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printExames(Collection<Exame> Exames,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printNotFound(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printImagens(Exame exame, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

	}

}
