package dev.sauloaraujo.sgb.evento.dominio;

import org.jmolecules.event.types.DomainEvent;

public interface EventoBarramento {
	<T extends DomainEvent> void adicionar(EventoObservador<T> observador);

	void postar(DomainEvent evento);
}