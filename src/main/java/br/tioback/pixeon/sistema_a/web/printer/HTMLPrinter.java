package br.tioback.pixeon.sistema_a.web.printer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.web.ReturnType;

public class HTMLPrinter extends ResponsePrinter {

	@Override
	protected ReturnType getReturnType() {
		return ReturnType.HTML;
	}

	private void beginPage(PrintWriter writer, String title) {
		writer.write("<html><head><title>" + title
				+ "</title></head><body><h1>" + title + "</h1>");
	}

	private void endPage(PrintWriter writer) {
		writer.write("<a href=\"index.html\">Menu inicial</a></body></html>");
	}

	@Override
	public void printPacientes(Collection<Paciente> pacientes,
			HttpServletResponse response) throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Pacientes");
		writer.write("<ol>");
		for (Paciente paciente : pacientes) {
			writer.write("<li>");
			printPaciente(writer, paciente);
			writer.write("</li>");
		}
		writer.write("</ol>");
		endPage(writer);
	}

	@Override
	public void printPaciente(Paciente paciente, HttpServletResponse response)
			throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Paciente");
		printPaciente(writer, paciente);
		endPage(writer);
	}

	private void printPaciente(PrintWriter writer, Paciente paciente) {
		writer.write("<b>Nome</b>: " + paciente.getNome() + "<br /><b>Id</b>: "
				+ paciente.getId() + "<br /><a href=\"exame?pid="
				+ paciente.getId() + "\">Ver Exames</a>");
	}

	@Override
	public void printClinicas(Collection<Clinica> clinicas,
			HttpServletResponse response) throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Clinicas");
		writer.write("<ol>");
		for (Clinica clinica : clinicas) {
			writer.write("<li>");
			printClinica(writer, clinica);
			writer.write("</li>");
		}
		writer.write("</ol>");
		endPage(writer);
	}

	@Override
	public void printClinica(Clinica clinica, HttpServletResponse response)
			throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Clinica");
		printClinica(writer, clinica);
		endPage(writer);
	}

	private void printClinica(PrintWriter writer, Clinica clinica) {
		writer.write("<b>Nome</b>: " + clinica.getNome() + "<br /><b>Id</b>: "
				+ clinica.getId());
	}

	@Override
	public void printExames(Collection<Exame> exames,
			HttpServletResponse response) throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Exames");
		writer.write("<ol>");
		for (Exame exame : exames) {
			writer.write("<li>");
			printExame(writer, exame);
			writer.write("</li>");
		}
		writer.write("</ol>");
		endPage(writer);
	}

	@Override
	public void printExame(Exame exame, HttpServletResponse response)
			throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Exame");
		printExame(writer, exame);
		endPage(writer);
	}

	private void printExame(PrintWriter writer, Exame exame) {
		writer.write("<b>Clínica</b>: " + exame.getClinica().getId() + "-"
				+ exame.getClinica().getNome() + "<br /><b>Paciente</b>: "
				+ exame.getPaciente().getId() + "-"
				+ exame.getPaciente().getNome() + "<br /><b>Id</b>: "
				+ exame.getId() + "<br /><a href=\"imagem?eid=" + exame.getId()
				+ "\">Imagens</a>");
	}

	@Override
	public void printNotFound(HttpServletResponse response) throws IOException {
		prepareResponse(response);
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		PrintWriter writer = response.getWriter();
		beginPage(writer, "Não encontrado");
		endPage(writer);
	}

	@Override
	public void printImagens(Exame exame, HttpServletResponse response)
			throws IOException {
		prepareResponse(response);

		PrintWriter writer = response.getWriter();
		beginPage(writer, "Imagens");
		writer.write("<ul>");
		for (Long imagemId : exame.getImagens()) {
			writer.write("<li>");
			writer.write("<a href=\"imagem?eid=" + exame.getId() + "&iid="
					+ imagemId + "\">Visualizar imagem " + imagemId + "</a>");
			writer.write("</li>");
		}
		writer.write("</ul>");
		endPage(writer);
	}

}
