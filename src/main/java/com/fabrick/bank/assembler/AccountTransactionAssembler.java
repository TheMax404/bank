package com.fabrick.bank.assembler;

import com.fabrick.bank.controller.model.AccountTransactionResponseModel;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountTransactionAssembler implements BaseAssembler<AccountTransactionResponseDto, AccountTransactionResponseModel> {

    @Override
    public AccountTransactionResponseModel toModel(AccountTransactionResponseDto dto) {
        if (dto == null) {
            return null;
        }
        AccountTransactionResponseModel model = new AccountTransactionResponseModel();
        if (dto.getList() != null) {
            List<AccountTransactionResponseModel.Transaction> transactions = dto.getList().stream().map(transactionDto -> {
                AccountTransactionResponseModel.Transaction transactionModel = new AccountTransactionResponseModel.Transaction();
                transactionModel.setTransactionId(transactionDto.getTransactionId());
                transactionModel.setOperationId(transactionDto.getOperationId());
                transactionModel.setAccountingDate(transactionDto.getAccountingDate());
                transactionModel.setValueDate(transactionDto.getValueDate());
                transactionModel.setAmount(transactionDto.getAmount());
                transactionModel.setCurrency(transactionDto.getCurrency());
                transactionModel.setDescription(transactionDto.getDescription());
                if (transactionDto.getType() != null) {
                    AccountTransactionResponseModel.Transaction.Type typeModel = new AccountTransactionResponseModel.Transaction.Type();
                    typeModel.setEnumeration(transactionDto.getType().getEnumeration());
                    typeModel.setValue(transactionDto.getType().getValue());
                    transactionModel.setType(typeModel);
                }
                return transactionModel;
            }).toList();
            model.setTransactions(transactions);
        }
        return model;
    }
}

