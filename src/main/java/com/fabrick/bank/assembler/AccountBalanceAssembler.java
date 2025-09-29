package com.fabrick.bank.assembler;

import com.fabrick.bank.controller.model.AccountBalanceResponseModel;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceAssembler implements BaseAssembler<AccountBalanceResponseDto, AccountBalanceResponseModel> {

    @Override
    public AccountBalanceResponseModel toModel(AccountBalanceResponseDto dto) {
        if (dto == null) {
            return null;
        }
        AccountBalanceResponseModel model = new AccountBalanceResponseModel();
        model.setBalance(dto.getBalance());
        model.setAvaiableBalance(dto.getAvaiableBalance());
        model.setCurrency(dto.getCurrency());
        model.setDate(dto.getDate());
        return model;
    }
}
