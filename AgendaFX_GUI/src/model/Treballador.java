package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Treballador {
	
	private Integer idTreballador;
	private LocalTime entrada;
	private LocalTime sortida;
	
	public Treballador(Integer idTreballador, LocalTime entrada, LocalTime sortida) {
		super();
		this.idTreballador = idTreballador;
		this.entrada = entrada;
		this.sortida = sortida;
	}

	public Integer getIdTreballador() {
		return idTreballador;
	}

	public void setIdTreballador(Integer idTreballador) {
		this.idTreballador = idTreballador;
	}

	public LocalTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalTime entrada) {
		this.entrada = entrada;
	}

	public LocalTime getSortida() {
		return sortida;
	}

	public void setSortida(LocalTime sortida) {
		this.sortida = sortida;
	}

	@Override
	public String toString() {
		return "Treballador [idTreballador=" + idTreballador + ", entrada=" + entrada + ", sortida=" + sortida + "]";
	}
	
	

	
}
