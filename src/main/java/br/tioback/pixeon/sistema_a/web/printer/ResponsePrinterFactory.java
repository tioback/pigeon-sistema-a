package br.tioback.pixeon.sistema_a.web.printer;

import br.tioback.pixeon.sistema_a.web.ReturnType;

public class ResponsePrinterFactory {

	public static ResponsePrinter newPrinter(ReturnType returnType) {
		switch (returnType) {
		case PDF:
			return new PDFPrinter();
		case JSON:
			return new JSONPrinter();
		case HTML:
		default:
			return new HTMLPrinter();
		}
	}

}
