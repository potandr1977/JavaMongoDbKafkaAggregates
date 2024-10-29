package com.potandr1977.mongoPoc.persons.handlers;

import an.awesome.pipelinr.Command;
import com.potandr1977.mongoPoc.annotations.ExecutionTimeCount;
import com.potandr1977.mongoPoc.persons.KafkaSender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PingHandler implements Command.Handler<PingCommand, String> {

    @Autowired
    private KafkaSender kafkaSender;

    @ExecutionTimeCount
    public void sendMessage(String data) {
        try {
            //kafkaSender.sendMessage("java-topic",data);
            var o1 = new SomeObject(1);
            var o2 = new SomeObject(1);

            if (o1.hashCode() == o2.hashCode())
            {
                Thread.sleep(10000);
            }

            Thread.sleep(5000);
        }
        catch(Exception ex)
        {
            var mes  = ex.getMessage();
        }
    }

    @Override
    public String handle(PingCommand command) {
        sendMessage(command.data);

        return "Pong from "+command.data;
    }

    public class SomeObject{

        @Getter
        @Setter
        public int value;

        public SomeObject(int value){
            this.value = value;
        }
    }
}

