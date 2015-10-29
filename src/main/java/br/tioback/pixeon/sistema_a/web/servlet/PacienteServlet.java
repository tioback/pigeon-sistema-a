package br.tioback.pixeon.sistema_a.web.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.service.PacienteService;
import br.tioback.pixeon.sistema_a.web.ReturnType;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinter;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinterFactory;

@WebServlet(description = "Servlet que trata requisições da entidade Paciente", urlPatterns = { "/paciente" })
public class PacienteServlet extends SistemaAServlet {
	private static final long serialVersionUID = 1L;

	private PacienteService service;

	public PacienteServlet() {
		service = new PacienteService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ReturnType returnType = getReturnType(request);
		ResponsePrinter printer = ResponsePrinterFactory.newPrinter(returnType);

		String pacienteId = request.getParameter("pid");
		if (pacienteId == null) {
			Collection<Paciente> pacientes = service.getAll();
			printer.printPacientes(pacientes, response);
			return;
		}

		Paciente paciente = service.get(Long.valueOf(pacienteId));
		if (paciente == null) {
			printer.printNotFound(response);
			return;
		}

		printer.printPaciente(paciente, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		service.save(extraiPaciente(request));
		request.getRequestDispatcher("sucesso.html").forward(request, response);
	}

	private Paciente extraiPaciente(HttpServletRequest request) {
		String pacienteId = request.getParameter("id");
		return new Paciente(pacienteId == null ? null
				: Long.valueOf(pacienteId), request.getParameter("nome"));
	}

	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		notImplemented(request, response);
	}

	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		notImplemented(request, response);
	}

}
