package dev.practice.remit.domain.remit;

public interface RemitReader {
    Remit getRemitBy(String remitToken);

    Remit getRemitByOrderToken(String orderToken);
}
