package br.tioback.pixeon.sistema_a.persistence.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.persistence.PacienteDAO;

public class PacienteMemoryDAO extends ProtoMemoryDAO implements PacienteDAO {

	@Override
	public Paciente buscaPorId(Long pacienteId) {
		return getPacientes().get(pacienteId);
	}

	@Override
	public Collection<Paciente> buscaTodos() {
		return Collections.unmodifiableCollection(getPacientes().values());
	}

	@Override
	public void salva(Paciente paciente) {
		if (paciente.getId() == null) {
			paciente.setId(nextPacienteSequence());
		}
		getPacientes().put(paciente.getId(), paciente);
		getExamesDosPacientes().put(paciente, new HashSet<>());
	}

	@Override
	public void exclui(Long pacienteId) {
		getPacientes().remove(pacienteId);
	}
}
