package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<River> rivers;
	
	public Model(){
		dao = new RiversDAO();
	}
	
	public List<River> getAllRivers(){
		if(rivers==null)
			rivers = dao.getAllRivers();
		return rivers;
	}

	public InfoRiver getInfoRivers(River river) {
		
		return dao.getInfoPerFiume(river);
		
	}

}
