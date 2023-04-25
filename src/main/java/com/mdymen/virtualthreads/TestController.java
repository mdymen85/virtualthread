package com.mdymen.virtualthreads;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    private static Integer CONTADOR = 0;

    @RequestMapping(value = "/v1/thread", method = RequestMethod.POST)
    public ResponseEntity<Integer> thread() throws InterruptedException {
        Thread.sleep(4000);
        CONTADOR++;
        log.info("Thread {} - Instant : {} - Contador : {}", Thread.currentThread().getName(), Instant.now(), CONTADOR);
        return new ResponseEntity<Integer>(CONTADOR, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/virtualthread", method = RequestMethod.POST)
    public ResponseEntity<Integer> virtualthread() throws InterruptedException {
        Thread.startVirtualThread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CONTADOR++;
            log.info("Thread {} - Instant : {} - Contador : {}", Thread.currentThread().getName(), Instant.now(), CONTADOR);

        });
        return new ResponseEntity<Integer>(CONTADOR, HttpStatus.OK);
    }

}
