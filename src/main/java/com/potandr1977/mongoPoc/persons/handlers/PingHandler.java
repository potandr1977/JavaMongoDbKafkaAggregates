package com.potandr1977.mongoPoc.persons.handlers;

import an.awesome.pipelinr.Command;
import org.springframework.stereotype.Component;

@Component
public class PingHandler implements Command.Handler<PingCommand, String> {

    @Override
    public String handle(PingCommand command) {
        return "Pong from "+command.data;
    }
}