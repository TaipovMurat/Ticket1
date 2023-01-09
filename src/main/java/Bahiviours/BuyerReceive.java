package Bahiviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BuyerReceive extends Behaviour {

    private String book;
    private List<ACLMessage> answers;
    private int receiversCount;

    public BuyerReceive(String book, int receiversCount){
        this.book = book;
        this.receiversCount = receiversCount;
    }


    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.AGREE), MessageTemplate.MatchPerformative(ACLMessage.FAILURE));
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null){
            answers.add(receive);
        }
        if (answers != null){
            new ChooseWinner(answers);
        }else {
            log.warn("Buyer has no proposal");
        }
    }

    @Override
    public boolean done() {
//        return false;
        return receiversCount == answers.size();
    }
}
