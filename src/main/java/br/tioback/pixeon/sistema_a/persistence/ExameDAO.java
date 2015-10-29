package br.tioback.pixeon.sistema_a.persistence;

import java.util.Collection;

import br.tioback.pixeon.sistema_a.entity.Exame;

public interface ExameDAO {

	public Exame buscaPorId(Long exameId);

	public Collection<Exame> buscaTodos();

	public void salva(Exame exame);

	public void exclui(Long exameId);

	public Collection<Exame> buscaPorPaciente(Long pacienteId);
}
