package dev.practice.remit.interfaces.api;

import dev.practice.remit.application.RemitFacade;
import dev.practice.remit.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/remits")
public class RemitApiController {
    private final RemitFacade remitFacade;
    private final RemitDtoMapper remitDtoMapper;

    @GetMapping("/{remitToken}")
    public CommonResponse retrieveOrder(@PathVariable String remitToken) {
        var remitInfo = remitFacade.getOrder(remitToken);
        return CommonResponse.success(remitInfo);
    }

    @PostMapping
    public CommonResponse registerOrder(@RequestBody @Valid RemitDto.RegisterReq request) {
        var command = remitDtoMapper.of(request);
        var remitInfo = remitFacade.registerOrder(command);
        return CommonResponse.success(new RemitDto.RegisterRes(remitInfo));
    }

    @PostMapping("/{remitToken}/remit-processing")
    public CommonResponse requestPaymentProcessing(@PathVariable String remitToken) {
        remitFacade.requestPaymentProcessing(remitToken);
        return CommonResponse.success("OK");
    }

    @PostMapping("/{remitToken}/accept-remit")
    public CommonResponse acceptRemit(
            @PathVariable String remitToken,
            @RequestBody @Valid RemitDto.AcceptRemitReq request
    ) {
        var acceptCommand = remitDtoMapper.of(remitToken, request);
        remitFacade.acceptRemit(acceptCommand);
        return CommonResponse.success("OK");
    }
}
