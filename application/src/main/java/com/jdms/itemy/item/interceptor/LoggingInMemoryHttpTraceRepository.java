package com.jdms.itemy.item.interceptor;

import com.jdms.itemy.item.kafka.KafkaProducerService;
import com.jdms.itemy.item.kafka.LogBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoggingInMemoryHttpTraceRepository extends InMemoryHttpTraceRepository {
    private final KafkaProducerService kafkaProducerService;

    public void add(HttpTrace trace) {
        super.add(trace);
        var body = LogBody.builder()
                .trace(ToStringBuilder.reflectionToString(trace))
                        .request(ToStringBuilder.reflectionToString(trace.getRequest()))
                                .response(ToStringBuilder.reflectionToString(trace.getResponse())).build();
        kafkaProducerService.send("log-topic", trace.getTimeTaken().toString(), body);
    }
}
