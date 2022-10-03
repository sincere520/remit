package dev.practice.remit.domain.remit;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RemitInfo {
    private final String orderToken;
    private final String remitToken;
    private final Remit.PushType pushType;
    private final String remitReceiverName;
    private final String remitReceiverPhone;
    private final String remitMessage;

    public RemitInfo(Remit remit) {
        this.orderToken = remit.getOrderToken();
        this.remitToken = remit.getRemitToken();
        this.pushType = remit.getPushType();
        this.remitReceiverName = remit.getRemitReceiverName();
        this.remitReceiverPhone = remit.getRemitReceiverName();
        this.remitMessage = remit.getRemitMessage();
    }
}
