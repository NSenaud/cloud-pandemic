package fr.efrei.ficherasenaud.tp;

import java.util.ArrayList;
import java.util.List;

import fr.efrei.paumier.common.core.CityBorder;
import fr.efrei.paumier.common.networking.*;
import fr.efrei.paumier.common.selection.Selector;
import fr.efrei.paumier.common.time.EventQueue;

public abstract class EmigrationManager implements CityBorder, MessageProtocol {
	private final MigrationProcessor immigrationProcessor = new MigrationProcessor();

	protected final MessageChannelHost host;
	protected final Selector<MessageChannel> selector;
	protected final EventQueue queue;
	
	public EmigrationManager(MessageChannelHost host, EventQueue queue, Selector<MessageChannel> selector) {
		this.host = host;
		this.queue = queue;
		this.selector = selector;
	}

	@Override
	public boolean trySendingEmigrant(boolean isInfected) {
		if (this.host.getChannels().size() == 0)
			return false;
	
		MessageChannel channel = this.selector.selectAmong(this.host.getChannels());
		sendEmigrantToward(isInfected, channel);
				
		return true;
	}

	protected void sendEmigrantToward(boolean isInfected, MessageChannel channel) {
		MigrationMessage message = new MigrationMessage(isInfected);
		channel.sendMessage(message);
	}

	protected abstract void receiveImmigrantFrom(MessageChannel sender, MigrationMessage immigrantMessage);

	@Override
	public List<MessageProcessor<?>> getProcessors() {
		ArrayList<MessageProcessor<?>> processors = new ArrayList<MessageProcessor<?>>(); 
		
		processors.add(immigrationProcessor);
		
		return processors;
	}

	@Override
	public void processIncomingConnection(MessageChannel newConnection) {
	}

	private class MigrationProcessor implements MessageProcessor<MigrationMessage> {

		@Override
		public Class<?> getSupportedClass() {
			return MigrationMessage.class;
		}

		@Override
		public void processMessage(MessageChannel sender,
				MigrationMessage message) {
			receiveImmigrantFrom(sender, message);
		}
		
	}

	protected static class MigrationMessage implements Message {
		private static final long serialVersionUID = 4560083501581816566L;

		private final boolean infectedFlag;
		
		public MigrationMessage(boolean isInfected) {
			this.infectedFlag = isInfected;
		}
		
		public boolean isInfected() {
			return this.infectedFlag;
		}
	}

}


/*
	
	private Inhabitant inhabitant;
	private boolean isInfected;
	private City city;
	// MESSAGE MIGRATION TODO

	public EmigrationManager() {
		this.city = Parameters.city;
	}

	
	public void migrateAnHabitant() {
		// Choix d'un habitant hors quarantaine
		inhabitant = city.selectAmong(city.getAliveInhabitantsArray());
		while(city.getQuarantinedInhabitantsArray().contains(inhabitant))  
			inhabitant = city.selectAmong(city.getAliveInhabitantsArray());
		// Check s'il est infecte et le stocke
		isInfected = inhabitant.getInfected();
		// ENVOYER L EVENT
		
		// le supprime de la ville une fois parti
		city.getAliveInhabitantsArray().remove(inhabitant);
		if(isInfected) city.getInfectedInhabitantsArray().remove(inhabitant);
		else city.getHealthyInhabitantsArray().remove(inhabitant); 
	}

	@Override
	public boolean trySendingEmigrant(boolean isInfected) {
		// TODO Auto-generated method stub
		return false;
	}

}*/
