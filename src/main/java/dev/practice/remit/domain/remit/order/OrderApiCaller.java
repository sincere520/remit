package dev.practice.remit.domain.remit.order;

import dev.practice.remit.domain.remit.RemitCommand;

public interface OrderApiCaller {
    String registerRemitOrder(OrderApiCommand.Register request);

    void updateReceiverInfo(String orderToken, RemitCommand.Accept request);
}
