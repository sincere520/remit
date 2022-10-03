package dev.practice.remit.interfaces.message;


import dev.practice.remit.application.RemitFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemitSqsMessageListener {
    private final RemitFacade remitFacade;

    @SqsListener(value = "order-payComplete-live.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void readMessage(RemitPaymentCompleteMessage message) {
        var orderToken = message.getOrderToken();
        log.info("[RemitSqsMessageListener.readMessage] orderToken = {}", orderToken);
        remitFacade.completePayment(orderToken);
    }
}
