package br.tioback.pixeon.sistema_a.persistence.memory;

import br.tioback.pixeon.sistema_a.persistence.ClinicaDAO;
import br.tioback.pixeon.sistema_a.persistence.ExameDAO;
import br.tioback.pixeon.sistema_a.persistence.PacienteDAO;

public class DAOFactory {

	public static ClinicaDAO newClinicaDao() {
		return new ClinicaMemoryDAO();
	}

	public static PacienteDAO newPacienteDao() {
		return new PacienteMemoryDAO();
	}

	public static ExameDAO newExameDao() {
		return new ExameMemoryDAO();
	}

}
