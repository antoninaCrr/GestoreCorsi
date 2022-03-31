package it.polito.tdp.corsi.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	// conterrà tutti i metodi per interfacciarci con la tabella Corso del nostro DB

	public List<Corso> getCorsiByPeriodo(int periodo) {
		
		String sql = "SELECT * " // togliere \n e mettere gli spazi se la query è scritta su più righe
				+ "FROM corso "
				+ "WHERE pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		try {
	         Connection conn = ConnectDB.getConnection(); // apro una connessione con il DB
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setInt(1,periodo); // st.setTIPODATOPARAMETRO(posizione del ? in cui vogliamo inserire il parametro, nomeParametro)
	         ResultSet rs = st.executeQuery();
	         
	         while(rs.next()) { // se c'è una riga successiva questo metodo ritorna true, altrimenti false
	        	// se siamo qui è perchè c'è una riga
	        	 Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),
	        			   rs.getString("nome"),rs.getInt("pd")); // rs.getTipoDAATO("nomeColonnaNELDB")
	        	 result.add(c);
	        	 
	         }
	         st.close();
	         conn.close();
	         return result;
	         
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}	
	}
	
	public Map<Corso,Integer> getIscritti(int periodo){
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins AND c.pd = ? "
				+ "GROUP BY c.codins, c.crediti, c.nome, c.pd";
		Map<Corso,Integer> result = new HashMap<Corso,Integer>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,periodo); // i parametri partono da 1 .AAA.
			ResultSet rs = st.executeQuery(); // eseguo la query solo dopo aver settato il parametro
			
			while(rs.next()) {
				result.put(new Corso(rs.getString("codins"),rs.getInt("crediti"),
	        			   rs.getString("nome"),rs.getInt("pd")), rs.getInt("n"));
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
