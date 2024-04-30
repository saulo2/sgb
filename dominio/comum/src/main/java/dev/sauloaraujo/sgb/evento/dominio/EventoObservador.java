package dev.sauloaraujo.sgb.evento.dominio;

import org.jmolecules.event.types.DomainEvent;

public interface EventoObservador<E extends DomainEvent> {
	void observarEvento(E evento);
}