package io.github.sruby.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;

/**
 * trace id filter
 *
 * @author Sruby
 * @date 20/2/2023 11:50
 */

@Slf4j
@Component
public class TraceIdGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = "N/A";
        Object skywalingSpanObject = exchange.getAttributes().get("SKYWALING_SPAN");
        if (ObjectUtils.isNotEmpty(skywalingSpanObject)) {
            try {
                Field owner = FieldUtils.getField(skywalingSpanObject.getClass(), "owner", true);
                Object tracingContext = owner.get(skywalingSpanObject);
                Field segmentField = FieldUtils.getField(tracingContext.getClass(), "segment", true);
                Object segment = segmentField.get(tracingContext);
                Field relatedGlobalTraceIdField = FieldUtils.getField(segment.getClass(), "relatedGlobalTraceId", true);
                Object relatedGlobalTraceId = relatedGlobalTraceIdField.get(segment);
                String traceIdObject = relatedGlobalTraceId.toString();
                traceId = StringUtils.substringBetween(traceIdObject, "=", ")");
            } catch (Exception e) {
                log.warn("get TID failed", e);
            }
        }
        exchange.getResponse().getHeaders().add("TID",traceId);
        return chain.filter(exchange);
    }
}