package Bahiviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerReceive extends Behaviour {


    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.AGREE), MessageTemplate.MatchPerformative(ACLMessage.FAILURE));
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null){
            if (receive.getPerformative() == ACLMessage.AGREE){
                log.info("Deal's done! Book {} was sold for {}", book, receive.getContent());
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
