package dev.practice.remit.infrastructure.remit.order;

import dev.practice.remit.common.response.CommonResponse;
import dev.practice.remit.domain.remit.RemitCommand;
import dev.practice.remit.domain.remit.order.OrderApiCaller;
import dev.practice.remit.domain.remit.order.OrderApiCommand;
import dev.practice.remit.infrastructure.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApiCallerImpl implements OrderApiCaller {
    private final RetrofitUtils retrofitUtils;
    private final RetrofitOrderApi retrofitOrderApi;

    @Override
    public String registerRemitOrder(OrderApiCommand.Register request) {
        var call = retrofitOrderApi.registerOrder(request);
        return retrofitUtils.responseSync(call)
                .map(CommonResponse::getData)
                .map(RetrofitOrderApiResponse.Register::getOrderToken)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateReceiverInfo(String orderToken, RemitCommand.Accept request) {
        var call = retrofitOrderApi.updateReceiverInfo(orderToken, request);
        retrofitUtils.responseVoid(call);
    }
}
