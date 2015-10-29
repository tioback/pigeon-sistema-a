package br.tioback.pixeon.sistema_a.web.printer;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.web.ReturnType;

public abstract class ResponsePrinter {

	public static final String ENCODING = "UTF-8";

	public abstract void printPaciente(Paciente paciente,
			HttpServletResponse response) throws IOException;

	public abstract void printPacientes(Collection<Paciente> pacientes,
			HttpServletResponse response) throws IOException;

	public abstract void printClinica(Clinica clinica,
			HttpServletResponse response) throws IOException;

	public abstract void printClinicas(Collection<Clinica> clinicas,
			HttpServletResponse response) throws IOException;

	public abstract void printExame(Exame exame, HttpServletResponse response)
			throws IOException;

	public abstract void printExames(Collection<Exame> Exames,
			HttpServletResponse response) throws IOException;

	protected void prepareResponse(HttpServletResponse response) {
		prepareResponse(response, getReturnType());
	}

	protected void prepareResponse(HttpServletResponse response,
			ReturnType returnType) {
		response.setCharacterEncoding(ENCODING);
		response.setContentType(returnType.getContentType());
		response.setStatus(HttpServletResponse.SC_OK);
	}

	public void printNotFound(HttpServletResponse response) throws IOException {
		prepareResponse(response, getReturnType());
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	public void printBadRequest(HttpServletResponse response)
			throws IOException {
		prepareResponse(response, getReturnType());
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	protected abstract ReturnType getReturnType();

	public abstract void printImagens(Exame exame, HttpServletResponse response)
			throws IOException;

	public void printRedirect(HttpServletResponse response, String uriImagem)
			throws IOException {
		prepareResponse(response, getReturnType());
		response.setHeader("Location", uriImagem);
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);

	}

	public void printNotImplemented(HttpServletResponse response) {
		prepareResponse(response, getReturnType());
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

}
