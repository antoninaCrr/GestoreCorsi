package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudentiByCorso(String codins) {
		String sql = "SELECT s.matricola,s.cognome,s.nome,s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		List<Studente> result = new ArrayList<Studente>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,codins); // i parametri partono da 1 .AAA.
			ResultSet rs = st.executeQuery(); // eseguo la query solo dopo aver settato il parametro
			
			while(rs.next()) {
				result.add(new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),
							rs.getString("CDS")));
			}
			rs.close();
			st.close();
			conn.close();
			
			return result;
		
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Divisione> getDivisioneStudenti(String codins){
		String sql = "SELECT s.CDS, COUNT (*) AS n "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.cds <> \'\' "
				+ "GROUP BY s.CDS ";
		List<Divisione> result = new ArrayList<Divisione>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,codins); // i parametri partono da 1 .AAA.
			ResultSet rs = st.executeQuery(); // eseguo la query solo dopo aver settato il parametro
			
			while(rs.next()) {
				result.add(new Divisione(rs.getString("CDS"),rs.getInt("n")));
			}
			rs.close();
			st.close();
			conn.close();
			
			return result;
		
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
	}

}
