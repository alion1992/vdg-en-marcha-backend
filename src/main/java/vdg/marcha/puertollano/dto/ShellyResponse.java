package vdg.marcha.puertollano.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShellyResponse {

    private boolean success;

    private String message;

}