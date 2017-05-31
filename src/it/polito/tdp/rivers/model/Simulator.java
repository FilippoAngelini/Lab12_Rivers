package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	private float q;
	float c;
	float fOutMin;
	int contDisservizio;
	float capCumulata;
	int cont;
	
	PriorityQueue<Event> queue;
	
	public Simulator(float k, float fmed){
		q = k*fmed*3600*24*30;
		c = q/2;
		fOutMin = (float) (0.8*fmed);
		queue = new PriorityQueue<Event>();
		contDisservizio=0;
		capCumulata = c;
		cont = 0;
	}
	
	public void load(List<Flow> flows){
		
		//Collections.sort(flows);
		
		System.out.println(flows.size());
		
		for(Flow f : flows)
			queue.add(new Event (f.getDay(),EventType.IN,f));
	}
	
	public void run(){
		
		while (!queue.isEmpty()) { 
			Event e = queue.poll(); 
			 
			switch (e.getType()) { 
				case IN: 
					this.handleIn(e); 
					break; 
			} 
		} 
	}
	private void handleIn(Event e) {
		
		cont++;
		
		double flowIn = e.getFlow().getFlow();
		
		double random = Math.random();
		
		double flowOut = fOutMin;
		
		if(random <= 0.05)
			flowOut = 10*fOutMin;
		
		double differenza = flowIn - flowOut;
		
		differenza = differenza * 3600*24;
		
		if(c + differenza > q) //TRACIMAZIONE
			c = q;
		else{
			if(c + differenza < 0){
				contDisservizio++;
				c=0;
			}
			else
				c += differenza;
		}
		
		capCumulata += c; 
	}

	public Risultato getRisultati(){
		
		float cMed = capCumulata/cont;
		
		return new Risultato (contDisservizio, cMed);
	}
}
