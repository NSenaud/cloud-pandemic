package fr.efrei.ficherasenaud.tp.common;

import java.util.List;

public interface Selectore<TItem> {
	TItem selectAmong(List<TItem> choices);
}
