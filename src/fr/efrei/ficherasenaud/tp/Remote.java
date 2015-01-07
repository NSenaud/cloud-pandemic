package fr.efrei.ficherasenaud.tp;

import fr.efrei.paumier.common.networking.BaseRemoteCityBorder;
import fr.efrei.paumier.common.networking.MessageChannel;
import fr.efrei.paumier.common.networking.MessageChannelHost;
import fr.efrei.paumier.common.selection.Selector;
import fr.efrei.paumier.common.time.EventQueue;

public class Remote extends BaseRemoteCityBorder {
	private City city;
	
	public Remote(MessageChannelHost host, EventQueue queue, Selector<MessageChannel> selector) {
		super(host, queue, selector);
		
		this.city = Parameters.city;
	}

	@Override
	protected void receiveImmigrantFrom(MessageChannel sender, MigrationMessage immigrantMessage) {
		Inhabitant emigrant = new Inhabitant();
		emigrant.setInfected(immigrantMessage.isInfected());
		this.city.addInhabitant(emigrant);
	}
}
