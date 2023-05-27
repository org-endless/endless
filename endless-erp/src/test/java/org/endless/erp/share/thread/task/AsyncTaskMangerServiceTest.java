package org.endless.erp.share.thread.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AsyncTaskMangerServiceTest {
    @Autowired
    private AsyncTaskMangerService service;

    @Test
    void test() {
        service.create("123_123", AsyncTaskManager.Status.create);
    }

}