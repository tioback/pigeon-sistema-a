package br.tioback.pixeon.sistema_a.entity;

import java.util.HashSet;
import java.util.Set;

public class Exame {

	private Long id;
	private Long pacienteId;
	private Long clinicaId;

	private Paciente paciente;
	private Clinica clinica;
	private Set<Long> imagens;

	public Exame(Long id, Paciente paciente, Clinica clinica) {
		super();
		this.id = id;
		this.pacienteId = paciente == null ? null : paciente.getId();
		this.clinicaId = clinica == null ? null : clinica.getId();
		this.paciente = paciente;
		this.clinica = clinica;
		this.imagens = new HashSet<>();
	}

	public Exame() {
		this(null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getClinicaId() {
		return clinicaId;
	}

	public void setClinicaId(Long clinicaId) {
		this.clinicaId = clinicaId;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Set<Long> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Long> imagens) {
		this.imagens = imagens;
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
		Exame other = (Exame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exame [id=" + id + ", pacienteId=" + pacienteId
				+ ", clinicaId=" + clinicaId + ", paciente=" + paciente
				+ ", clinica=" + clinica + ", imagens=" + imagens + "]";
	}

}
