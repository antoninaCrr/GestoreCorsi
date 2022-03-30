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
}
