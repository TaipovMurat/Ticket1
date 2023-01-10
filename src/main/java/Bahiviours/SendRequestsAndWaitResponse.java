package Bahiviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SendRequestsAndWaitResponse extends ParallelBehaviour {
    private long timeout;
    private List<AID> rec;
    private List<ACLMessage> answers;
    private String book;
    private List<ACLMessage> positive = new ArrayList<>();

    public SendRequestsAndWaitResponse(Agent a, long timeout, List<AID> receivers, List<ACLMessage> answers, String book) {
        super(a, WHEN_ANY);
        this.timeout = timeout;
        this.rec = receivers;
        this.answers = answers;
        this.book = book;
    }

    @Override
    public void onStart() {
        WakerBehaviour waker = new WakerBehaviour(myAgent, this.timeout) {
            @Override
            protected void onWake() {
                log.info("Time out is over");
            }
        };

        Behaviour worker = new Behaviour() {
//            final MessageTemplate mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), MessageTemplate.MatchPerformative(ACLMessage.FAILURE));
            final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);

            @Override
            public void onStart() {
                ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
                rec.forEach(el -> m.addReceiver(el));
                m.setContent(book);
                myAgent.send(m);
            }

            @Override
            public void action() {
                ACLMessage ans = myAgent.receive(mt);
                if (ans != null){
                    answers.add(ans);
                    if (ans.getPerformative() == ACLMessage.PROPOSE){
                        log.info("I received answer form {} with price {}",ans.getSender().getLocalName(), ans.getContent());
                        positive.add(ans);
                    }else {
                        log.info("Seller {} don't have this book", ans.getSender().getLocalName());
                    }
                } else {
                    block();
                }

            }

            @Override
            public boolean done() {
                return answers.size() == rec.size();
            }
        };

        addSubBehaviour(waker);
        addSubBehaviour(worker);
        if (positive.size() != 0){
            log.info("The winner is {}", new ChooseWinner(positive));
        }
    }

}