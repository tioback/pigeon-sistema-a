package br.tioback.pixeon.sistema_a.persistence.memory;

import java.util.Collection;
import java.util.Collections;

import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.persistence.ExameDAO;

public class ExameMemoryDAO extends ProtoMemoryDAO implements ExameDAO {

	@Override
	public Exame buscaPorId(Long exameId) {
		return getExames().get(exameId);
	}

	@Override
	public Collection<Exame> buscaPorPaciente(Long pacienteId) {
		Paciente paciente = getPacientes().get(pacienteId);
		if (paciente == null) {
			return null;
		}
		return getExamesDosPacientes().get(paciente);
	}

	@Override
	public Collection<Exame> buscaTodos() {
		return Collections.unmodifiableCollection(getExames().values());
	}

	@Override
	public void salva(Exame exame) {
		if (exame.getId() == null) {
			exame.setId(nextExameSequence());
		}
		exame.setClinica(getClinicas().get(exame.getClinicaId()));
		exame.setPaciente(getPacientes().get(exame.getPacienteId()));
		getExames().put(exame.getId(), exame);
		getExamesDosPacientes().get(exame.getPaciente()).add(exame);
	}

	@Override
	public void exclui(Long exameId) {
		getExames().remove(exameId);
	}
}
