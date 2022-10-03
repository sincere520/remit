package dev.practice.remit.domain.remit;

import dev.practice.remit.domain.remit.order.OrderApiCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class RemitCommand {

    @Getter
    @Builder
    @ToString
    public static class Register {
        private final Long senderUserId;
        private final String payMethod;
        private final String pushType;
        private final String remitReceiverName;
        private final String remitReceiverPhone;
        private final String remitMessage;
        private final List<RegisterOrderAccount> orderAccountList;

        public Remit toEntity(String orderToken) {
            return Remit.builder()
                    .senderUserId(senderUserId)
                    .orderToken(orderToken)
                    .pushType(Remit.PushType.valueOf(pushType))
                    .remitReceiverName(remitReceiverName)
                    .remitReceiverPhone(remitReceiverPhone)
                    .remitMessage(remitMessage)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccount {
        private final String orderCount;
        private final String accountToken;
        private final String accountName;
        private final String accountAmt;
        private final List<RegisterOrderAccountOptionGroup> orderAccountOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccountOptionGroup {
        private final Integer ordering;
        private final String accountOptionGroupName;
        private final List<OrderApiCommand.RegisterOrderAccountOption> orderAccountOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccountOption {
        private final Integer ordering;
        private final String accountOptionName;
        private final Long accountOptionAmt;
    }

    @Getter
    @Builder
    @ToString
    public static class Accept {
        private final String remitToken;
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
    }
}
