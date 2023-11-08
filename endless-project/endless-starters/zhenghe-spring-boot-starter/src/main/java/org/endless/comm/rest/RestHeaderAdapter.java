package org.endless.comm.rest;

import org.springframework.web.client.RestTemplate;

/**
 * RestHeaderAdapter
 *
 * @author Deng Haozhi
 * @date 2023/4/8 22:21
 * @since 0.0.2
 */
public class RestHeaderAdapter {

    private final RestTemplate restTemplate;

    public RestHeaderAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
