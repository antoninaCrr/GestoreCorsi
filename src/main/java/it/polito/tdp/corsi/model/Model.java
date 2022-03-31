package it.polito.tdp.corsi.model;

import java.util.*;
import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;


public class Model {
	
	private CorsoDAO corsoDAO; // conviene creare un attributo DAO da usare in ogni metodo che si interfaccia alle rispettive classi DAO
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo){
		return this.corsoDAO.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso,Integer> getIscritti(int periodo){
		return this.corsoDAO.getIscritti(periodo);
	}
	
	public List<Studente> getStudentiByCorso(String codins){
		return this.studenteDAO.getStudentiByCorso(codins);
	}
	
	public List<Divisione> getDivisioneStudenti(String codins){
		return this.getDivisioneStudenti(codins);
	}
}
