package it.polito.tdp.corsi.model;

public class Divisione implements Comparable<Divisione> {
	
	// uso implements quando so che il criterio di ordinamento è sempre il medesimo

	private String CDS;
	private Integer n;
	
	public Divisione(String cDS, Integer n) {
		super();
		CDS = cDS;
		this.n = n;
	}

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	@Override
	public int compareTo(Divisione o) {
		// TODO Auto-generated method stub
		return (-1)* o.getCDS().compareTo(this.CDS); // voglio ordinare solo per CDS
	}
	
	// so che si tratta di una semplice classe d'appoggio, quindi non ho bisogno di avere hashcode e equals
	
	
}
