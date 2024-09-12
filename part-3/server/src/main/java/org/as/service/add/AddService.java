package org.as.service.add;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddService {
    public int add(int i, int j) {
        log.info("add:: {} + {}", i, j);
        return i + j;
    }
}
