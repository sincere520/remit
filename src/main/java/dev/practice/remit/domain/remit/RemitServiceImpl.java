package dev.practice.remit.domain.remit;

import dev.practice.remit.domain.remit.order.OrderApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemitServiceImpl implements RemitService {
    private final RemitReader remitReader;
    private final RemitStore remitStore;
    private final OrderApiCaller orderApiCaller;
    private final RemitToOrderMapper remitToOrderMapper;

    /**
     * 선물 주문 정보를 가져온다
     * 선물 수령자의 수락 페이지 로딩 시에 사용된다
     *
     * @param remitToken
     * @return
     */
    @Override
    public RemitInfo getRemitInfo(String remitToken) {
        var remit = remitReader.getRemitBy(remitToken);
        return new RemitInfo(remit);
    }

    /**
     * 선물하기 주문을 등록한다
     * 해당 주문을 주문 서비스에 등록하기 위해 API 를 호출하고
     * 등록된 주문의 식별키와 선물 관련 정보를 반영하여 Remit 도메인을 저장한다
     *
     * @param request
     * @return
     */
    @Override
    @Transactional
    public RemitInfo registerOrder(RemitCommand.Register request) {
        var orderCommand = remitToOrderMapper.of(request);
        var orderToken = orderApiCaller.registerRemitOrder(orderCommand);
        var initRemit = request.toEntity(orderToken);
        var remit = remitStore.store(initRemit);
        return new RemitInfo(remit);
    }

    /**
     * 선물하기 주문의 상태를 [결제중] 으로 변경한다
     *
     * @param remitToken
     */
    @Override
    @Transactional
    public void requestPaymentProcessing(String remitToken) {
        var remit = remitReader.getRemitBy(remitToken);
        remit.inPayment();
    }

    /**
     * 주문 서비스에서 결제 완료 후 orderToken 을 메시징으로 발행하면
     * 선물하기 서비스에서 이를 읽어서 선물 주문의 결제를 완료 상태로 변경한다
     *
     * @param orderToken
     */
    @Override
    @Transactional
    public void completePayment(String orderToken) {
        var remit = remitReader.getRemitByOrderToken(orderToken);
        remit.completePayment();
    }

    /**
     * 선물 수령자가 배송지를 입력하고 [선물 수락] 하면
     * 선물 상태를 Accept 로 변경하고, 주문 서비스 API 를 호출하여 주문의 배송지 주소를 업데이트 한다
     *
     * @param request
     */
    @Override
    @Transactional
    public void acceptRemit(RemitCommand.Accept request) {
        var remitToken = request.getRemitToken();
        var remit = remitReader.getRemitBy(remitToken);
        remit.accept(request);

        orderApiCaller.updateReceiverInfo(remit.getOrderToken(), request);
    }
}
