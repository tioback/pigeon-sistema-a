package br.tioback.pixeon.sistema_a.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.service.ClinicaService;
import br.tioback.pixeon.sistema_a.service.ExameService;
import br.tioback.pixeon.sistema_a.web.ReturnType;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinter;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinterFactory;

@WebServlet(description = "Servlet que trata requisições da entidade Imagem", urlPatterns = { "/imagem" })
public class ImagensServlet extends SistemaAServlet {
	private static final long serialVersionUID = 1L;

	private ExameService exameService;
	private ClinicaService clinicaService;

	public ImagensServlet() {
		exameService = new ExameService();
		clinicaService = new ClinicaService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReturnType returnType = getReturnType(request);
		ResponsePrinter printer = ResponsePrinterFactory.newPrinter(returnType);

		String exameId = request.getParameter("eid");
		if (exameId == null) {
			printer.printBadRequest(response);
			return;
		}

		Exame exame = exameService.get(Long.valueOf(exameId));
		if (exame == null) {
			printer.printNotFound(response);
			return;
		}

		String imagemId = request.getParameter("iid");
		if (imagemId != null) {
			String uriImagem = clinicaService.getUriImagem(exame, imagemId);
			printer.printRedirect(response, uriImagem);
			return;
		}

		printer.printImagens(exame, response);
	}

}
