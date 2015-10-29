package br.tioback.pixeon.sistema_a.web.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.service.ClinicaService;
import br.tioback.pixeon.sistema_a.web.ReturnType;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinter;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinterFactory;

@WebServlet(description = "Servlet que trata requisições da entidade Clínica", urlPatterns = { "/clinica" })
public class ClinicaServlet extends SistemaAServlet {
	private static final long serialVersionUID = 1L;

	private ClinicaService service;

	public ClinicaServlet() {
		service = new ClinicaService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReturnType returnType = getReturnType(request);
		ResponsePrinter printer = ResponsePrinterFactory.newPrinter(returnType);

		String clinicaId = request.getParameter("cid");
		if (clinicaId == null) {
			Collection<Clinica> clinicas = service.getAll();
			printer.printClinicas(clinicas, response);
			return;
		}

		Clinica clinica = service.get(Long.valueOf(clinicaId));
		if (clinica == null) {
			printer.printNotFound(response);
			return;
		}

		printer.printClinica(clinica, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		service.save(extrairClinica(request));
		request.getRequestDispatcher("sucesso.html").forward(request, response);
	}

	private Clinica extrairClinica(HttpServletRequest request) {
		String clinicaId = request.getParameter("id");
		return new Clinica(clinicaId == null ? null : Long.valueOf(clinicaId),
				request.getParameter("nome"),
				request.getParameter("uriTemplate"), false);
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
