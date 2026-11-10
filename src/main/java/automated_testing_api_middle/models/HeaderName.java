package automated_testing_api_middle.models;

import lombok.Getter;

@Getter
public enum HeaderName {

    AUTHORIZATION("Authorization");

    private final String value;

    HeaderName(String value) {
        this.value = value;
    }
}
