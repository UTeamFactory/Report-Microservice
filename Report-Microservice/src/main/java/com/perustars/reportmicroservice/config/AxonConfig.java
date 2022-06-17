package com.perustars.reportmicroservice.config;

import com.perustars.reportmicroservice.command.domain.entities.Report;
import com.thoughtworks.xstream.XStream;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[] {
                "com.perustars.reportcontracts.**"
        });
        return xStream;
    }

    @Bean
    public Repository<Report> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Report.class)
                .eventStore(eventStore)
                .build();
    }
}
