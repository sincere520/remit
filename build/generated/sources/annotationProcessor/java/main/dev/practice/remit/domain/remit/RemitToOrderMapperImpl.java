package dev.practice.remit.domain.remit;

import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccount;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccountOptionGroup;
import dev.practice.remit.domain.remit.order.OrderApiCommand.Register;
import dev.practice.remit.domain.remit.order.OrderApiCommand.Register.RegisterBuilder;
import dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccount.RegisterOrderAccountBuilder;
import dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption;
import dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption.RegisterOrderAccountOptionBuilder;
import dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOptionGroup.RegisterOrderAccountOptionGroupBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T21:47:59+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class RemitToOrderMapperImpl implements RemitToOrderMapper {

    @Override
    public Register of(dev.practice.remit.domain.remit.RemitCommand.Register register) {
        if ( register == null ) {
            return null;
        }

        RegisterBuilder register1 = Register.builder();

        register1.senderUserId( register.getSenderUserId() );
        register1.payMethod( register.getPayMethod() );
        register1.orderAccountList( registerOrderAccountListToRegisterOrderAccountList( register.getOrderAccountList() ) );

        return register1.build();
    }

    @Override
    public dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccount of(RegisterOrderAccount register) {
        if ( register == null ) {
            return null;
        }

        RegisterOrderAccountBuilder registerOrderAccount = dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccount.builder();

        registerOrderAccount.orderCount( register.getOrderCount() );
        registerOrderAccount.accountToken( register.getAccountToken() );
        registerOrderAccount.accountName( register.getAccountName() );
        registerOrderAccount.accountAmt( register.getAccountAmt() );
        registerOrderAccount.orderAccountOptionGroupList( registerOrderAccountOptionGroupListToRegisterOrderAccountOptionGroupList( register.getOrderAccountOptionGroupList() ) );

        return registerOrderAccount.build();
    }

    @Override
    public dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOptionGroup of(RegisterOrderAccountOptionGroup register) {
        if ( register == null ) {
            return null;
        }

        RegisterOrderAccountOptionGroupBuilder registerOrderAccountOptionGroup = dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOptionGroup.builder();

        registerOrderAccountOptionGroup.ordering( register.getOrdering() );
        registerOrderAccountOptionGroup.accountOptionGroupName( register.getAccountOptionGroupName() );
        List<RegisterOrderAccountOption> list = register.getOrderAccountOptionList();
        if ( list != null ) {
            registerOrderAccountOptionGroup.orderAccountOptionList( new ArrayList<RegisterOrderAccountOption>( list ) );
        }

        return registerOrderAccountOptionGroup.build();
    }

    @Override
    public RegisterOrderAccountOption of(dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccountOption register) {
        if ( register == null ) {
            return null;
        }

        RegisterOrderAccountOptionBuilder registerOrderAccountOption = RegisterOrderAccountOption.builder();

        registerOrderAccountOption.ordering( register.getOrdering() );
        registerOrderAccountOption.accountOptionName( register.getAccountOptionName() );
        registerOrderAccountOption.accountOptionAmt( register.getAccountOptionAmt() );

        return registerOrderAccountOption.build();
    }

    protected List<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccount> registerOrderAccountListToRegisterOrderAccountList(List<RegisterOrderAccount> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccount> list1 = new ArrayList<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccount>( list.size() );
        for ( RegisterOrderAccount registerOrderAccount : list ) {
            list1.add( of( registerOrderAccount ) );
        }

        return list1;
    }

    protected List<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOptionGroup> registerOrderAccountOptionGroupListToRegisterOrderAccountOptionGroupList(List<RegisterOrderAccountOptionGroup> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOptionGroup> list1 = new ArrayList<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOptionGroup>( list.size() );
        for ( RegisterOrderAccountOptionGroup registerOrderAccountOptionGroup : list ) {
            list1.add( of( registerOrderAccountOptionGroup ) );
        }

        return list1;
    }
}
