package dev.practice.remit.domain.remit;

import dev.practice.remit.common.exception.IllegalStatusException;
import dev.practice.remit.common.exception.InvalidParamException;
import dev.practice.remit.common.util.TokenGenerator;
import dev.practice.remit.domain.AbstractEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "remits")
public class Remit extends AbstractEntity {

    private static final String GIFT_PREFIX = "rm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String remitToken;
    private Long senderUserId;
    private String orderToken;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PushType pushType;
    private String remitReceiverName;
    private String remitReceiverPhone;
    private String remitMessage;

    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private String receiverAddress1;
    private String receiverAddress2;
    private String etcMessage;

    private ZonedDateTime paidAt;
    private ZonedDateTime pushedAt;
    private ZonedDateTime acceptedAt;
    private ZonedDateTime expiredAt;

    @Getter
    @AllArgsConstructor
    public enum Status {
        INIT("선물 주문 생성"),
        WAIT("대기중"),
        REMIT_COMPLETE("송금 완료"),
        PUSH_COMPLETE("송금수락 링크 발송 완료"),
        ACCEPT("수락"),
        DELIVERY_PREPARE("상품준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료"),
        EXPIRATION("선물 수락 만료");

        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum PushType {
        KAKAO("카카오톡"),
        LMS("문자");

        private final String description;
    }

    @Builder
    public Remit(
            Long senderUserId,
            String orderToken,
            PushType pushType,
            String remitReceiverName,
            String remitReceiverPhone,
            String remitMessage
    ) {
        if (senderUserId == null) throw new InvalidParamException("Remit constructor senderUserId is null");
        if (pushType == null) throw new InvalidParamException("Remit constructor pushType is null");
        if (StringUtils.isEmpty(remitReceiverName)) throw new InvalidParamException("Remit constructor remitReceiverName is empty");
        if (StringUtils.isEmpty(remitReceiverPhone)) throw new InvalidParamException("Remit constructor remitReceiverPhone is empty");
        if (StringUtils.isEmpty(remitMessage)) throw new InvalidParamException("Remit constructor remitMessage is empty");

        this.remitToken = TokenGenerator.randomCharacterWithPrefix(GIFT_PREFIX);
        this.senderUserId = senderUserId;
        this.orderToken = orderToken;
        this.status = Status.INIT;
        this.pushType = pushType;
        this.remitReceiverName = remitReceiverName;
        this.remitReceiverPhone = remitReceiverPhone;
        this.remitMessage = remitMessage;
        this.expiredAt = ZonedDateTime.now().plusDays(7);
    }

    public void inPayment() {
        if (this.status != Status.INIT) throw new IllegalStatusException("Remit inPayment");
        this.status = Status.WAIT;
    }

    public void completePayment() {
        if (this.status != Status.WAIT) throw new IllegalStatusException("Remit paymentComplete");
        this.status = Status.REMIT_COMPLETE;
        this.paidAt = ZonedDateTime.now();
    }

    public void pushLink() {
        if (this.status != Status.REMIT_COMPLETE) throw new IllegalStatusException("Remit pushLink");
        this.status = Status.PUSH_COMPLETE;
        this.pushedAt = ZonedDateTime.now();
    }

    public void accept(RemitCommand.Accept request) {
        var receiverName = request.getReceiverName();
        var receiverPhone = request.getReceiverPhone();
        //var receiverZipcode = request.getReceiverZipcode();
        //var receiverAddress1 = request.getReceiverAddress1();
        //var receiverAddress2 = request.getReceiverAddress2();
        var receiverZipcode = " ";
        var receiverAddress1 = " ";
        var receiverAddress2 = " ";
        var etcMessage = request.getEtcMessage();

        if (!availableAccept()) throw new IllegalStatusException();
        if (StringUtils.isEmpty(receiverName)) throw new InvalidParamException("Remit accept receiverName is empty");
        if (StringUtils.isEmpty(receiverPhone)) throw new InvalidParamException("Remit accept receiverPhone is empty");
        if (StringUtils.isEmpty(receiverZipcode)) throw new InvalidParamException("Remit accept receiverZipcode is empty");
        if (StringUtils.isEmpty(receiverAddress1)) throw new InvalidParamException("Remit accept receiverAddress1 is empty");
        if (StringUtils.isEmpty(receiverAddress2)) throw new InvalidParamException("Remit accept receiverAddress2 is empty");
        if (StringUtils.isEmpty(etcMessage)) throw new InvalidParamException("Remit accept etcMessage is empty");

        this.status = Status.ACCEPT;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.etcMessage = etcMessage;
        this.acceptedAt = ZonedDateTime.now();
    }

    public void expired() {
        this.status = Status.EXPIRATION;
        this.expiredAt = ZonedDateTime.now();
    }

    private boolean availableAccept() {
        if (this.expiredAt.isBefore(ZonedDateTime.now())) return false;
        return this.status == Status.REMIT_COMPLETE || this.status == Status.PUSH_COMPLETE;
    }
}
