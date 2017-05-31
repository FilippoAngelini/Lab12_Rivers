package it.polito.tdp.rivers.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.InfoRiver;
import it.polito.tdp.rivers.model.River;

public class RiversDAO {

	public List<River> getAllRivers() {
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException();
		}

		return rivers;
	}

	public List<Flow> getAllFlows(List<River> rivers) {
		final String sql = "SELECT id, day, flow, river FROM flow";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"),
						rivers.get(rivers.indexOf(new River(res.getInt("river"))))));
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException();
		}

		return flows;
	}
	
	public InfoRiver getInfoPerFiume(River river){
		final String sql = "SELECT * FROM flow WHERE river=?";

		InfoRiver info;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();
			
			float flow=0;
			int cont=0;
			LocalDate prima = LocalDate.now();
			LocalDate ultima = LocalDate.now();

			while (res.next()) {
				flow += res.getFloat("flow");
				cont++;
			}
			
			flow = flow/cont;
			if(res.first())
				prima = res.getDate("day").toLocalDate();
			if(res.last())
				ultima = res.getDate("day").toLocalDate();
			
			info = new InfoRiver (prima, ultima, cont, flow);

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException();
		}

		return info;
	}

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();

		List<River> rivers = dao.getAllRivers();
		System.out.println(rivers);

		List<Flow> flows = dao.getAllFlows(rivers);
		System.out.format("Loaded %d flows\n", flows.size());
		// System.out.println(flows) ;
	}

}
