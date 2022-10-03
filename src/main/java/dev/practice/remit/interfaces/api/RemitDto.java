package dev.practice.remit.interfaces.api;

import dev.practice.remit.domain.remit.RemitInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RemitDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterReq {
        @NotNull(message = "userId 는 필수값입니다")
        private Long senderUserId;

        //@NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        @NotBlank(message = "pushType 는 필수값입니다")
        private String pushType;

        @NotBlank(message = "remitReceiverName 는 필수값입니다")
        private String remitReceiverName;

        @NotBlank(message = "remitReceiverPhone 는 필수값입니다")
        private String remitReceiverPhone;

        @NotBlank(message = "remitMessage 는 필수값입니다")
        private String remitMessage;

        private List<RegisterOrderAccountReq> orderAccountList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderAccountReq {
        @NotNull(message = "orderCount 는 필수값입니다")
        private Integer orderCount;

        @NotBlank(message = "accountToken 는 필수값입니다")
        private String accountToken;

        @NotBlank(message = "accountName 는 필수값입니다")
        private String accountName;

        @NotNull(message = "accountAmt 는 필수값입니다")
        private Long accountAmt;

        private List<RegisterOrderAccountOptionGroupReq> orderAccountOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderAccountOptionGroupReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "accountOptionGroupName 는 필수값입니다")
        private String accountOptionGroupName;

        private List<RegisterOrderAccountOptionReq> orderAccountOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderAccountOptionReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "accountOptionName 는 필수값입니다")
        private String accountOptionName;

        @NotNull(message = "accountOptionAmt 는 필수값입니다")
        private Long accountOptionAmt;
    }

    @Getter
    public static class RegisterRes {
        private final String orderToken;
        private final String remitToken;

        public RegisterRes(RemitInfo remitInfo) {
            this.orderToken = remitInfo.getOrderToken();
            this.remitToken = remitInfo.getRemitToken();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class CompletePaymentReq {
        private String orderToken;
    }

    @Getter
    @Setter
    @ToString
    public static class AcceptRemitReq {
        @NotBlank(message = "receiverName 는 필수값입니다")
        private String receiverName;

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        private String receiverPhone;

        //@NotBlank(message = "receiverZipcode 는 필수값입니다")
        private String receiverZipcode;

        //@NotBlank(message = "receiverAddress1 는 필수값입니다")
        private String receiverAddress1;

        //@NotBlank(message = "receiverAddress2 는 필수값입니다")
        private String receiverAddress2;

        @NotBlank(message = "etcMessage 는 필수값입니다")
        private String etcMessage;
    }
}
