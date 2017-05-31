package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	InfoRiver info;
    River river;
	
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
		
		if(info==null || !this.river.equals(river)){
    		this.river = river;
    		info = dao.getInfoPerFiume(this.river);
    	}
		
		return this.info;
		
	}
	
	public Risultato doSimula(float k, River river){
		
		List<Flow> flows = dao.getAllFlowsForRiver(river);
		
		InfoRiver info = this.getInfoRivers(river);
		
		Simulator sim = new Simulator(k,info.getFmed());
		
		sim.load(flows);
		sim.run();
		return sim.getRisultati();
	}

}
