package br.tioback.pixeon.sistema_a.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tioback.pixeon.sistema_a.web.ReturnType;
import br.tioback.pixeon.sistema_a.web.printer.ResponsePrinterFactory;

public abstract class SistemaAServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected ReturnType getReturnType(HttpServletRequest request) {
		return ReturnType.getByMimeType(request.getHeader("Accept"));
	}

	protected void notImplemented(HttpServletRequest request,
			HttpServletResponse response) {
		ResponsePrinterFactory.newPrinter(getReturnType(request))
				.printNotImplemented(response);
	}

}
