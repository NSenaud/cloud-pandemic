package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import javax.xml.datatype.DatatypeFactory;

import java.util.ArrayList;
import java.util.Date;




import fr.efrei.ficherasenaud.tp.common.Event;
import fr.efrei.ficherasenaud.tp.common.EventQueue;
import fr.efrei.ficherasenaud.tp.Eventt;

public class EventQueuee implements EventQueue {
	private ArrayList<Event> EventList;
	private Date current_date;
	private Date last_date;
	private Date beginning;
	private boolean isEnd ;

	public EventQueuee() {
		beginning = new Date();
		isEnd = false;
	}

	@Override
	public void register(Event... event) {
		for(Event eve : event)
			EventList.add(eve);
	}
	
	public void EndEvent(){
		isEnd = true;
	}
	
	public void update(){
		for(Event eve : EventList){
			//eve.setDuration( eve.getBaseDuration() - (newDuration(current_date.getSeconds() - last_date.getSeconds() )) );
		}
	}
}
