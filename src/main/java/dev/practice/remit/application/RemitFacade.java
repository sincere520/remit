package dev.practice.remit.application;

import dev.practice.remit.domain.remit.RemitCommand;
import dev.practice.remit.domain.remit.RemitInfo;
import dev.practice.remit.domain.remit.RemitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemitFacade {
    private final RemitService remitService;

    public RemitInfo getOrder(String remitToken) {
        log.info("===RemitFacade.getOrder===");
        log.info("getOrder remitToken = {}", remitToken);
        return remitService.getRemitInfo(remitToken);
    }

    public RemitInfo registerOrder(RemitCommand.Register command) {
        log.info("===RemitFacade.registerOrder===");
        var remitInfo = remitService.registerOrder(command);
        log.info("registerOrder orderToken = {}", remitInfo);
        return remitInfo;
    }

    public void requestPaymentProcessing(String remitToken) {
        log.info("===RemitFacade.requestPaymentProcessing===");
        remitService.requestPaymentProcessing(remitToken);
    }

    public void completePayment(String orderToken) {
        log.info("===RemitFacade.completePayment===");
        remitService.completePayment(orderToken);
    }

    public void acceptRemit(RemitCommand.Accept request) {
        log.info("===RemitFacade.acceptRemit===");
        remitService.acceptRemit(request);
    }
}
