package br.tioback.pixeon.sistema_a.persistence;

import java.util.Collection;

import br.tioback.pixeon.sistema_a.entity.Clinica;

public interface ClinicaDAO {

	public Clinica buscaPorId(Long clinicaId);

	public Collection<Clinica> buscaTodas();

	public void salva(Clinica clinica);

	public void exclui(Long clinicaId);
}
