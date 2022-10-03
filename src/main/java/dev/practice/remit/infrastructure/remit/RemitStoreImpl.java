package dev.practice.remit.infrastructure.remit;

import dev.practice.remit.common.exception.InvalidParamException;
import dev.practice.remit.domain.remit.Remit;
import dev.practice.remit.domain.remit.RemitStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemitStoreImpl implements RemitStore {
    private final RemitRepository remitRepository;

    @Override
    public Remit store(Remit remit) {
        if (remit == null) throw new InvalidParamException();
        return remitRepository.save(remit);
    }
}
