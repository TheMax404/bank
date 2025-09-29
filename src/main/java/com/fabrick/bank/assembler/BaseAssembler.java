package com.fabrick.bank.assembler;

public interface BaseAssembler<D, M> {
    M toModel(D dto);
}
