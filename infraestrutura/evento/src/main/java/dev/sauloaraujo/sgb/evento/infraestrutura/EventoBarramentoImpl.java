package dev.sauloaraujo.sgb.evento.infraestrutura;

import static org.apache.commons.lang3.Validate.notNull;

import org.jmolecules.event.types.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import dev.sauloaraujo.sgb.evento.dominio.EventoBarramento;
import dev.sauloaraujo.sgb.evento.dominio.EventoObservador;

@Component
public class EventoBarramentoImpl implements EventoBarramento {
	@Autowired
	private ApplicationEventMulticaster multicaster;

	@Autowired
	private ApplicationEventPublisher publicador;

	@Override
	public <T extends DomainEvent> void adicionar(EventoObservador<T> observador) {
		notNull(observador, "O observador não pode ser nulo");

		multicaster.addApplicationListener(new ApplicationListener<PayloadApplicationEvent<T>>() {
			public void onApplicationEvent(PayloadApplicationEvent<T> evento) {
				observador.observarEvento(evento.getPayload());
			};
		});
	}

	@Override
	public void postar(DomainEvent evento) {
		publicador.publishEvent(evento);
	}
}