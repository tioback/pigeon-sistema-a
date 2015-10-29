package br.tioback.pixeon.sistema_a.service;

import java.util.Collection;

import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.persistence.memory.DAOFactory;

public class ExameService {

	public Exame get(Long exameId) {
		return DAOFactory.newExameDao().buscaPorId(exameId);
	}

	public Collection<Exame> getAll() {
		return DAOFactory.newExameDao().buscaTodos();
	}

	public void save(Exame exame) {
		DAOFactory.newExameDao().salva(exame);
	}

	public void delete(Long exameId) {
		DAOFactory.newExameDao().exclui(exameId);
	}

	public Collection<Exame> getAllByPatient(Long pacienteId) {
		return DAOFactory.newExameDao().buscaPorPaciente(pacienteId);
	}

}
