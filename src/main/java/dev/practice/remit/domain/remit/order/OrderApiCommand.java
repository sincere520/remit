package dev.practice.remit.domain.remit.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class OrderApiCommand {

    @Getter
    @Builder
    @ToString
    public static class Register {
        private final Long senderUserId;
        private final String payMethod;
        private final List<RegisterOrderAccount> orderAccountList;
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
        private final List<RegisterOrderAccountOption> orderAccountOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccountOption {
        private final Integer ordering;
        private final String accountOptionName;
        private final Long accountOptionAmt;
    }
}

