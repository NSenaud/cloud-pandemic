package fr.efrei.ficherasenaud.tp;

import fr.efrei.paumier.common.time.Event;
import fr.efrei.paumier.common.time.EventQueue;

import java.util.ArrayList;
import java.util.Date;

/**
 * @class Queue
 * 
 *
 */
public class EventQueueP implements EventQueue {
	private ArrayList<EventP> EventList;
	private Date current_date;
	private Date last_date;
	private Date beginning;

	public EventQueueP() {
		beginning = new Date();
	}

	@Override
	public void register(Event... event) {
		for(EventP eve : (EventP[]) event)
			EventList.add(eve);
	}
	
	public void update(){
		current_date = new Date();
		for(EventP eve : EventList){
			 //int res = eve.getBaseDuration().compareTo( Duration.between(current_date , eve.getBeginning() )  );
			 if(true)
				 eve.trigger();
		}
	}
}
