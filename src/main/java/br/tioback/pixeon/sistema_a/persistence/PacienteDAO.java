package br.tioback.pixeon.sistema_a.persistence;

import java.util.Collection;

import br.tioback.pixeon.sistema_a.entity.Paciente;

public interface PacienteDAO {

	public Paciente buscaPorId(Long pacienteId);

	public Collection<Paciente> buscaTodos();

	public void salva(Paciente paciente);

	public void exclui(Long pacienteId);
}
