package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vdg.marcha.puertollano.config.ShellyProperties;

@Service
@RequiredArgsConstructor
public class ShellyService {

    private final RestTemplate restTemplate;

    private final ShellyProperties shellyProperties;

    public void abrirPuerta() {

        String onUrl =
                "http://" +
                        shellyProperties.getIp() +
                        "/relay/" +
                        shellyProperties.getRelay() +
                        "?turn=on";

        String offUrl =
                "http://" +
                        shellyProperties.getIp() +
                        "/relay/" +
                        shellyProperties.getRelay() +
                        "?turn=off";

        restTemplate.getForObject(
                onUrl,
                String.class
        );

        try {
            Thread.sleep(
                    shellyProperties.getPulseDuration()
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        restTemplate.getForObject(
                offUrl,
                String.class
        );
    }

}