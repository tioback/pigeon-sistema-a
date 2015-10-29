package br.tioback.pixeon.sistema_a.service;

import java.util.Collection;

import br.tioback.pixeon.sistema_a.entity.Paciente;
import br.tioback.pixeon.sistema_a.persistence.memory.DAOFactory;

public class PacienteService {

	public Paciente get(Long pacienteId) {
		return DAOFactory.newPacienteDao().buscaPorId(pacienteId);
	}

	public Collection<Paciente> getAll() {
		return DAOFactory.newPacienteDao().buscaTodos();
	}

	public void save(Paciente paciente) {
		DAOFactory.newPacienteDao().salva(paciente);
	}

	public void delete(Long pacienteId) {
		DAOFactory.newPacienteDao().exclui(pacienteId);
	}

}
