package dev.practice.remit.infrastructure.remit;

import dev.practice.remit.common.exception.EntityNotFoundException;
import dev.practice.remit.common.exception.InvalidParamException;
import dev.practice.remit.domain.remit.Remit;
import dev.practice.remit.domain.remit.RemitReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemitReaderImpl implements RemitReader {
    private final RemitRepository remitRepository;

    @Override
    public Remit getRemitBy(String remitToken) {
        if (StringUtils.isEmpty(remitToken)) throw new InvalidParamException();
        return remitRepository.findByRemitToken(remitToken).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Remit getRemitByOrderToken(String orderToken) {
        if (StringUtils.isEmpty(orderToken)) throw new InvalidParamException();
        return remitRepository.findByOrderToken(orderToken).orElseThrow(EntityNotFoundException::new);
    }
}
