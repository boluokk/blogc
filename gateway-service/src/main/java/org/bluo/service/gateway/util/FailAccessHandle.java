package org.bluo.service.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bluo.common.util.respons.Result;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 访问失败，返回处理
 *
 * @author boluo
 * @date 2024/01/31
 */
@Slf4j
public class FailAccessHandle {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Mono<Void> fail(String message, ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory buffered = response.bufferFactory();
            try {
                return buffered.wrap(OBJECT_MAPPER.writeValueAsBytes(Result.fail(message)));
            } catch (JsonProcessingException e) {
                log.error("Error writing response", e);
                return buffered.wrap(new byte[0]);
            }
        }));
    }
}
