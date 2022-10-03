package dev.practice.remit.interfaces.api;

import dev.practice.remit.domain.remit.RemitCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RemitDtoMapper {

    RemitCommand.Register of(RemitDto.RegisterReq request);

    RemitCommand.RegisterOrderAccount of(RemitDto.RegisterOrderAccountReq request);

    RemitCommand.RegisterOrderAccountOptionGroup of(RemitDto.RegisterOrderAccountOptionGroupReq request);

    RemitCommand.RegisterOrderAccountOption of(RemitDto.RegisterOrderAccountOptionReq request);

    RemitCommand.Accept of(String remitToken, RemitDto.AcceptRemitReq request);
}
