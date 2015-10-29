package br.tioback.pixeon.sistema_a.web.printer;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.web.ReturnType;

import com.google.gson.Gson;

public class JSONPrinter extends ResponsePrinter {

	@Override
	protected ReturnType getReturnType() {
		return ReturnType.JSON;
	}

	private void commonPrint(Object object, HttpServletResponse response)
			throws IOException {
		prepareResponse(response);
		response.getWriter().write(new Gson().toJson(object));
	}

	@Override
	public void printPaciente(Paciente paciente, HttpServletResponse response)
			throws IOException {
		commonPrint(paciente, response);
	}

	@Override
	public void printPacientes(Collection<Paciente> pacientes,
			HttpServletResponse response) throws IOException {
		commonPrint(pacientes, response);
	}

	@Override
	public void printClinica(Clinica clinica, HttpServletResponse response)
			throws IOException {
		commonPrint(clinica, response);
	}

	@Override
	public void printClinicas(Collection<Clinica> clinicas,
			HttpServletResponse response) throws IOException {
		commonPrint(clinicas, response);
	}

	@Override
	public void printExame(Exame exame, HttpServletResponse response)
			throws IOException {
		commonPrint(exame, response);
	}

	@Override
	public void printExames(Collection<Exame> exames,
			HttpServletResponse response) throws IOException {
		commonPrint(exames, response);
	}

	@Override
	public void printImagens(Exame exame, HttpServletResponse response)
			throws IOException {
		commonPrint(exame, response);
	}
}
