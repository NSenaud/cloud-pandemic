package fr.efrei.ficherasenaud.tp.network;

import java.util.List;
import java.util.Random;

import fr.efrei.paumier.common.selection.Selector;
import fr.efrei.paumier.common.networking.MessageChannel;

public class Available implements Selector<MessageChannel> {
	public Available() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MessageChannel selectAmong(List<MessageChannel> choices) {
		if (choices.size() > 0) {
			Random rand = new Random();
			int index = rand.nextInt(choices.size());
			
			return choices.get(index);
		}
		else {
			return null;
		}
	}
}
