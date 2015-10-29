package br.tioback.pixeon.sistema_a.service;

import java.util.Collection;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.persistence.memory.DAOFactory;

public class ClinicaService {

	public Clinica get(Long clinicaId) {
		return DAOFactory.newClinicaDao().buscaPorId(clinicaId);
	}

	public Collection<Clinica> getAll() {
		return DAOFactory.newClinicaDao().buscaTodas();
	}

	public void save(Clinica clinica) {
		DAOFactory.newClinicaDao().salva(clinica);
	}

	public void delete(Long clinicaId) {
		DAOFactory.newClinicaDao().exclui(clinicaId);
	}

	public String getUriImagem(Exame exame, String imagemId) {
		return exame
				.getClinica()
				.getUriTemplate()
				.replaceAll("\\$\\{imagemId\\}", imagemId)
				.replaceAll("\\$\\{clinicaId\\}",
						exame.getClinicaId().toString())
				.replaceAll("\\$\\{exameId\\}", exame.getId().toString())
				.replaceAll("\\$\\{pacienteId\\}",
						exame.getPacienteId().toString());
	}

}
