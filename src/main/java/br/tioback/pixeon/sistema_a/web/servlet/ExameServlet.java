package br.tioback.pixeon.sistema_a.web.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.service.ExameService;
import br.tioback.pixeon.sistema_a.web.ReturnType;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinter;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinterFactory;

@WebServlet(description = "Servlet que trata requisições da entidade Exame", urlPatterns = { "/exame" })
public class ExameServlet extends SistemaAServlet {
	private static final long serialVersionUID = 1L;

	private ExameService service;

	public ExameServlet() {
		service = new ExameService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReturnType returnType = getReturnType(request);
		ResponsePrinter printer = ResponsePrinterFactory.newPrinter(returnType);

		String exameId = request.getParameter("eid");
		String pacienteId = request.getParameter("pid");
		if (exameId == null && pacienteId == null) {
			Collection<Exame> exames = service.getAll();
			printer.printExames(exames, response);
			return;
		}

		if (exameId == null && pacienteId != null) {
			Collection<Exame> exames = service.getAllByPatient(Long
					.valueOf(pacienteId));
			if (exames == null) {
				printer.printNotFound(response);
				return;
			}

			printer.printExames(exames, response);
			return;
		}

		Exame exame = service.get(Long.valueOf(exameId));
		if (exame == null) {
			printer.printNotFound(response);
			return;
		}

		printer.printExame(exame, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		service.save(extrairExame(request));
		request.getRequestDispatcher("sucesso.html").forward(request, response);
	}

	private Exame extrairExame(HttpServletRequest request) {
		String exameId = request.getParameter("id");
		Exame exame = new Exame();
		exame.setId(exameId == null ? null : Long.valueOf(exameId));
		exame.setClinicaId(Long.valueOf(request.getParameter("clinicaId")));
		exame.setPacienteId(Long.valueOf(request.getParameter("pacienteId")));
		for (long i = 0; i < Integer.valueOf(request.getParameter("imagens")); i++) {
			exame.getImagens().add(i);
		}
		return exame;
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
