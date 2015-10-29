package br.tioback.pixeon.sistema_a.web;

public enum ReturnType {

	HTML("text/html"), JSON("text/json"), PDF("application/pdf");

	private final String contentType;

	private ReturnType(final String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public static ReturnType getByMimeType(String mimeType) {
		if (mimeType == null) {
			return HTML;
		}

		for (ReturnType returnType : values()) {
			if (mimeType.contains(returnType.contentType)) {
				return returnType;
			}
		}
		return HTML;
	}
}
