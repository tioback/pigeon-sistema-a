package br.tioback.pixeon.sistema_a.persistence.memory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.tioback.pixeon.sistema_a.entity.Clinica;
import br.tioback.pixeon.sistema_a.entity.Exame;
import br.tioback.pixeon.sistema_a.entity.Paciente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class ProtoMemoryDAO {

	private static Map<Long, Clinica> clinicas;
	private static Map<Long, Paciente> pacientes;
	private static Map<Long, Exame> exames;
	private static Map<Paciente, Set<Exame>> examesDosPacientes;

	private static long clinicaSequence;
	private static long pacienteSequence;
	private static long exameSequence;

	static {
		preLoadData();
	}

	private static void preLoadData() {
		clinicaSequence = pacienteSequence = exameSequence = 0;
		clinicas = new HashMap<>();
		pacientes = new HashMap<>();
		exames = new HashMap<>();
		examesDosPacientes = new HashMap<>();

		try (Reader reader = new InputStreamReader(
				ProtoMemoryDAO.class.getResourceAsStream("sample_data.json"))) {
			Gson gson = new GsonBuilder().create();
			JsonElement jelem = new JsonParser().parse(reader);

			preLoadClinicas(gson, jelem);
			preLoadPacientes(gson, jelem);
			preLoadExames(gson, jelem);
			// preLoadImagens(gson, jelem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void preLoadClinicas(Gson gson, JsonElement jelem) {

		JsonArray clncs = jelem.getAsJsonObject().getAsJsonArray("clinicas");
		clncs.forEach(elem -> {
			Clinica clinica = gson.fromJson(elem, Clinica.class);
			clinicas.put(clinica.getId(), clinica);
			clinicaSequence++;
		});
	}

	private static void preLoadPacientes(Gson gson, JsonElement jelem) {

		JsonArray pcnts = jelem.getAsJsonObject().getAsJsonArray("pacientes");
		pcnts.forEach(elem -> {
			Paciente paciente = gson.fromJson(elem, Paciente.class);
			pacientes.put(paciente.getId(), paciente);
			examesDosPacientes.put(paciente, new HashSet<>());
			pacienteSequence++;
		});
	}

	private static void preLoadExames(Gson gson, JsonElement jelem) {

		JsonArray xms = jelem.getAsJsonObject().getAsJsonArray("exames");
		xms.forEach(elem -> {
			Exame exame = gson.fromJson(elem, Exame.class);
			exame.setClinica(clinicas.get(exame.getClinicaId()));
			Paciente paciente = pacientes.get(exame.getPacienteId());
			exame.setPaciente(paciente);
			Set<Exame> examesDoPaciente = examesDosPacientes.get(paciente);
			examesDoPaciente.add(exame);
			exames.put(exame.getId(), exame);
			exameSequence++;
		});
	}

	protected Long nextClinicaSequence() {
		return ++clinicaSequence;
	}

	protected Map<Long, Clinica> getClinicas() {
		return clinicas;
	}

	protected Long nextPacienteSequence() {
		return ++pacienteSequence;
	}

	protected Map<Long, Paciente> getPacientes() {
		return pacientes;
	}

	protected Long nextExameSequence() {
		return ++exameSequence;
	}

	protected Map<Long, Exame> getExames() {
		return exames;
	}

	protected Map<Paciente, Set<Exame>> getExamesDosPacientes() {
		return examesDosPacientes;
	}

}
