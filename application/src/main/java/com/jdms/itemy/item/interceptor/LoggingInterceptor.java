package com.jdms.itemy.item.interceptor;

import com.jdms.itemy.item.kafka.KafkaProducerService;
import com.jdms.itemy.item.kafka.LogBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private  KafkaProducerService kafkaProducerService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        var body = LogBody.builder()
                .request(ToStringBuilder.reflectionToString(request))
                .response(ToStringBuilder.reflectionToString(response)).build();
        kafkaProducerService.send("log-topic-1", handler.toString(), body);
    }
}
