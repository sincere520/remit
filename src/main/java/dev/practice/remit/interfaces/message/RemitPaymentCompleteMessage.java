package dev.practice.remit.interfaces.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class RemitPaymentCompleteMessage {
    private String orderToken;
}
