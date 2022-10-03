package dev.practice.remit.domain.remit;

import dev.practice.remit.domain.remit.order.OrderApiCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RemitToOrderMapper {

    OrderApiCommand.Register of(RemitCommand.Register register);

    OrderApiCommand.RegisterOrderAccount of(RemitCommand.RegisterOrderAccount register);

    OrderApiCommand.RegisterOrderAccountOptionGroup of(RemitCommand.RegisterOrderAccountOptionGroup register);

    OrderApiCommand.RegisterOrderAccountOption of(RemitCommand.RegisterOrderAccountOption register);
}
