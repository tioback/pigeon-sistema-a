package br.tioback.pixeon.sistema_a.entity;

public class Clinica {

	private Long id;
	private String nome;
	private String uriTemplate;
	private Boolean imageByRequest;

	public Clinica(Long id, String nome, String uriTemplate,
			Boolean imageByRequest) {
		super();
		this.id = id;
		this.nome = nome;
		this.uriTemplate = uriTemplate;
		this.imageByRequest = imageByRequest;
	}

	public Clinica() {
		this(null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUriTemplate() {
		return uriTemplate;
	}

	public void setUriTemplate(String uriTemplate) {
		this.uriTemplate = uriTemplate;
	}

	public Boolean isImageByRequest() {
		return Boolean.TRUE.equals(imageByRequest);
	}

	public void setImageByRequest(Boolean imageByRequest) {
		this.imageByRequest = imageByRequest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clinica other = (Clinica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clinica [id=" + id + ", nome=" + nome + ", uriTemplate="
				+ uriTemplate + ", imageByRequest=" + imageByRequest + "]";
	}

}
