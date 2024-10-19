package com.potandr1977.mongoPoc.persons.handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;

public class PingCommand implements Command<String> {

    public final String data;

    public PingCommand(String data)
    {
        this.data = data;
    }

}
