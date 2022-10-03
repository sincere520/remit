package dev.practice.remit.interfaces.api;

import dev.practice.remit.domain.remit.RemitCommand.Accept;
import dev.practice.remit.domain.remit.RemitCommand.Accept.AcceptBuilder;
import dev.practice.remit.domain.remit.RemitCommand.Register;
import dev.practice.remit.domain.remit.RemitCommand.Register.RegisterBuilder;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccount;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccount.RegisterOrderAccountBuilder;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccountOption;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccountOption.RegisterOrderAccountOptionBuilder;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccountOptionGroup;
import dev.practice.remit.domain.remit.RemitCommand.RegisterOrderAccountOptionGroup.RegisterOrderAccountOptionGroupBuilder;
import dev.practice.remit.interfaces.api.RemitDto.AcceptRemitReq;
import dev.practice.remit.interfaces.api.RemitDto.RegisterOrderAccountOptionGroupReq;
import dev.practice.remit.interfaces.api.RemitDto.RegisterOrderAccountOptionReq;
import dev.practice.remit.interfaces.api.RemitDto.RegisterOrderAccountReq;
import dev.practice.remit.interfaces.api.RemitDto.RegisterReq;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T21:48:00+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class RemitDtoMapperImpl implements RemitDtoMapper {

    @Override
    public Register of(RegisterReq request) {
        if ( request == null ) {
            return null;
        }

        RegisterBuilder register = Register.builder();

        register.senderUserId( request.getSenderUserId() );
        register.payMethod( request.getPayMethod() );
        register.pushType( request.getPushType() );
        register.remitReceiverName( request.getRemitReceiverName() );
        register.remitReceiverPhone( request.getRemitReceiverPhone() );
        register.remitMessage( request.getRemitMessage() );
        register.orderAccountList( registerOrderAccountReqListToRegisterOrderAccountList( request.getOrderAccountList() ) );

        return register.build();
    }

    @Override
    public RegisterOrderAccount of(RegisterOrderAccountReq request) {
        if ( request == null ) {
            return null;
        }

        RegisterOrderAccountBuilder registerOrderAccount = RegisterOrderAccount.builder();

        if ( request.getOrderCount() != null ) {
            registerOrderAccount.orderCount( String.valueOf( request.getOrderCount() ) );
        }
        registerOrderAccount.accountToken( request.getAccountToken() );
        registerOrderAccount.accountName( request.getAccountName() );
        if ( request.getAccountAmt() != null ) {
            registerOrderAccount.accountAmt( String.valueOf( request.getAccountAmt() ) );
        }
        registerOrderAccount.orderAccountOptionGroupList( registerOrderAccountOptionGroupReqListToRegisterOrderAccountOptionGroupList( request.getOrderAccountOptionGroupList() ) );

        return registerOrderAccount.build();
    }

    @Override
    public RegisterOrderAccountOptionGroup of(RegisterOrderAccountOptionGroupReq request) {
        if ( request == null ) {
            return null;
        }

        RegisterOrderAccountOptionGroupBuilder registerOrderAccountOptionGroup = RegisterOrderAccountOptionGroup.builder();

        registerOrderAccountOptionGroup.ordering( request.getOrdering() );
        registerOrderAccountOptionGroup.accountOptionGroupName( request.getAccountOptionGroupName() );
        registerOrderAccountOptionGroup.orderAccountOptionList( registerOrderAccountOptionReqListToRegisterOrderAccountOptionList( request.getOrderAccountOptionList() ) );

        return registerOrderAccountOptionGroup.build();
    }

    @Override
    public RegisterOrderAccountOption of(RegisterOrderAccountOptionReq request) {
        if ( request == null ) {
            return null;
        }

        RegisterOrderAccountOptionBuilder registerOrderAccountOption = RegisterOrderAccountOption.builder();

        registerOrderAccountOption.ordering( request.getOrdering() );
        registerOrderAccountOption.accountOptionName( request.getAccountOptionName() );
        registerOrderAccountOption.accountOptionAmt( request.getAccountOptionAmt() );

        return registerOrderAccountOption.build();
    }

    @Override
    public Accept of(String remitToken, AcceptRemitReq request) {
        if ( remitToken == null && request == null ) {
            return null;
        }

        AcceptBuilder accept = Accept.builder();

        if ( remitToken != null ) {
            accept.remitToken( remitToken );
        }
        if ( request != null ) {
            accept.receiverName( request.getReceiverName() );
            accept.receiverPhone( request.getReceiverPhone() );
            accept.receiverZipcode( request.getReceiverZipcode() );
            accept.receiverAddress1( request.getReceiverAddress1() );
            accept.receiverAddress2( request.getReceiverAddress2() );
            accept.etcMessage( request.getEtcMessage() );
        }

        return accept.build();
    }

    protected List<RegisterOrderAccount> registerOrderAccountReqListToRegisterOrderAccountList(List<RegisterOrderAccountReq> list) {
        if ( list == null ) {
            return null;
        }

        List<RegisterOrderAccount> list1 = new ArrayList<RegisterOrderAccount>( list.size() );
        for ( RegisterOrderAccountReq registerOrderAccountReq : list ) {
            list1.add( of( registerOrderAccountReq ) );
        }

        return list1;
    }

    protected List<RegisterOrderAccountOptionGroup> registerOrderAccountOptionGroupReqListToRegisterOrderAccountOptionGroupList(List<RegisterOrderAccountOptionGroupReq> list) {
        if ( list == null ) {
            return null;
        }

        List<RegisterOrderAccountOptionGroup> list1 = new ArrayList<RegisterOrderAccountOptionGroup>( list.size() );
        for ( RegisterOrderAccountOptionGroupReq registerOrderAccountOptionGroupReq : list ) {
            list1.add( of( registerOrderAccountOptionGroupReq ) );
        }

        return list1;
    }

    protected dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption registerOrderAccountOptionReqToRegisterOrderAccountOption(RegisterOrderAccountOptionReq registerOrderAccountOptionReq) {
        if ( registerOrderAccountOptionReq == null ) {
            return null;
        }

        dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption.RegisterOrderAccountOptionBuilder registerOrderAccountOption = dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption.builder();

        registerOrderAccountOption.ordering( registerOrderAccountOptionReq.getOrdering() );
        registerOrderAccountOption.accountOptionName( registerOrderAccountOptionReq.getAccountOptionName() );
        registerOrderAccountOption.accountOptionAmt( registerOrderAccountOptionReq.getAccountOptionAmt() );

        return registerOrderAccountOption.build();
    }

    protected List<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption> registerOrderAccountOptionReqListToRegisterOrderAccountOptionList(List<RegisterOrderAccountOptionReq> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption> list1 = new ArrayList<dev.practice.remit.domain.remit.order.OrderApiCommand.RegisterOrderAccountOption>( list.size() );
        for ( RegisterOrderAccountOptionReq registerOrderAccountOptionReq : list ) {
            list1.add( registerOrderAccountOptionReqToRegisterOrderAccountOption( registerOrderAccountOptionReq ) );
        }

        return list1;
    }
}
