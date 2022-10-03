package dev.practice.remit.infrastructure.remit.order;

import dev.practice.remit.common.response.CommonResponse;
import dev.practice.remit.domain.remit.RemitCommand;
import dev.practice.remit.domain.remit.order.OrderApiCommand;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitOrderApi {

    @POST("api/v1/remit-orders/init")
    Call<CommonResponse<RetrofitOrderApiResponse.Register>> registerOrder(@Body OrderApiCommand.Register request);

    @POST("api/v1/remit-orders/{orderToken}/update-receiver-info")
    Call<Void> updateReceiverInfo(@Path("orderToken") String orderToken, @Body RemitCommand.Accept request);
}
