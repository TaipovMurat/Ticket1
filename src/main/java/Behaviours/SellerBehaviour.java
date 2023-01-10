package Behaviours;

import Agents.SellerCfg;
import Helpers.DFHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class SellerBehaviour extends Behaviour {


    private SellerCfg books;
    private int price;
    private int low = 200;
    private int high = 400;
    Random r = new Random();
    private List<AID> agents;
    ACLMessage msg = new ACLMessage(ACLMessage.CFP);
    private boolean response;

    public SellerBehaviour(SellerCfg books) {
        this.books = books;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null){
            agents = DFHelper.findAgents(getAgent(),"Buyer");
//            log.info("books {}", books);
            if (books.getBookName().contains(receive.getContent())){
                price = r.nextInt(high-low)+low;
                log.info("It's {} dollars for {}", price, receive.getContent());
                msg.setPerformative(ACLMessage.PROPOSE);
                msg.setContent(String.valueOf(price));
            }else {
                log.info("Seller {} don't have needed book", getAgent().getLocalName());
                msg.setPerformative(ACLMessage.FAILURE);
                msg.setContent("I'm sorry :( ");
            }


            for (AID agent: agents){
                msg.addReceiver(agent);
            }
            getAgent().send(msg);
            response = true;


        }else {
            block();
        }

    }

    @Override
    public boolean done() {
        return response;
    }
}
