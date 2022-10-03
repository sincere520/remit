package dev.practice.remit.domain.remit;

public interface RemitService {

    RemitInfo getRemitInfo(String remitToken);

    RemitInfo registerOrder(RemitCommand.Register request);

    void requestPaymentProcessing(String remitToken);

    void completePayment(String orderToken);

    void acceptRemit(RemitCommand.Accept request);
}
