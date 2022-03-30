package it.polito.tdp.corsi.model;

import java.util.*;
import it.polito.tdp.corsi.db.CorsoDAO;


public class Model {
	
	private CorsoDAO corsoDAO; // conviene creare un attributo DAO da usare in ogni metodo che si interfaccia alle rispettive classi DAO
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo){
		return this.corsoDAO.getCorsiByPeriodo(periodo);
	}
}
