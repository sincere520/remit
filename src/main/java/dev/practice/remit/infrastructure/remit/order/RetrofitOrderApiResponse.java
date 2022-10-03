package dev.practice.remit.infrastructure.remit.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class RetrofitOrderApiResponse {

    @Getter
    @Builder
    @ToString
    public static class Register {
        private final String orderToken;
    }
}
