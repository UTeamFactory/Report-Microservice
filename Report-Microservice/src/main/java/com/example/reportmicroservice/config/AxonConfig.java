package com.example.reportmicroservice.config;

import com.example.reportmicroservice.command.domain.entities.Report;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
public class AxonConfig {
    @Bean
    public Repository<Report> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Report.class)
                .eventStore(eventStore)
                .build();
    }
}
