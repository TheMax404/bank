package com.fabrick.bank.command;

public interface BaseCommand<I, O> {
        O execute();
}
