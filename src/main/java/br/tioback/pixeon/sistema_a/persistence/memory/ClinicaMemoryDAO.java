package br.tioback.pixeon.sistema_a.persistence.memory;

import java.util.Collection;
import java.util.Collections;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.persistence.ClinicaDAO;

public class ClinicaMemoryDAO extends ProtoMemoryDAO implements ClinicaDAO {

	@Override
	public Clinica buscaPorId(Long clinicaId) {
		return getClinicas().get(clinicaId);
	}

	@Override
	public Collection<Clinica> buscaTodas() {
		return Collections.unmodifiableCollection(getClinicas().values());
	}

	@Override
	public void salva(Clinica clinica) {
		if (clinica.getId() == null) {
			clinica.setId(nextClinicaSequence());
		}
		getClinicas().put(clinica.getId(), clinica);
	}

	@Override
	public void exclui(Long clinicaId) {
		getClinicas().remove(clinicaId);
	}
}
