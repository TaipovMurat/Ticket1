package Behaviours;

import Helpers.DFHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BuyerRequest extends Behaviour {
    // you could make it OneShotBehaviour
    private List<AID> agents;
//    private int bookNum = (int) Math.random() * 4;
    private String book;
    private boolean sendRequest;


    public  BuyerRequest (String book){
        this.book = book;
    }

    @Override
    public void action() {
        agents =  DFHelper.findAgents(getAgent(),"Seller");
        log.info("Found {} sellers", agents.size());
        ACLMessage request = new ACLMessage( ACLMessage.REQUEST);
        for (AID agent: agents){
            request.addReceiver(agent);
        }
        request.setContent(book); // Set content for message
        getAgent().send(request); // Send request for seleers
        log.info("Buyer send his request for book [{}]", book);
        getAgent().addBehaviour(new BuyerReceive(book, agents.size()));
        sendRequest = true;
    }

    @Override
    public boolean done() {
        return sendRequest;
    }
}
