package Agents;


import Bahiviours.BuyerReceive;
import Bahiviours.BuyerRequest;
import Bahiviours.SendRequestsAndWaitResponse;
import Helpers.DFHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class BuyerAgent extends Agent {
    private String neededBook;
    private int bookNum = new Random().nextInt(6);
//    private int bookNum = 1;
    private String book;
    private List<AID> receivers;

    @Override
    protected void setup() {
//        log.info("Buyer woke up");
        DFHelper.registerAgent(this, "Buyer");
        switch (bookNum){
            case 0:
                book = "War and peace";
                break;
            case 1:
                book = "Oblomov";
                break;
            case 2:
                book = "Green mile";
                break;
            case 3:
                book = "Idiot";
                break;
            case 4:
                book = "Captain's daughter";
                break;
            case 5:
                book = "Shine";
        }
        log.info("Buyer looking for {}", book);
        addBehaviour(new BuyerRequest(book));
//        receivers = DFHelper.findAgents(this,"Seller");
//        List<ACLMessage> ans = new ArrayList<>();
//        addBehaviour(new SendRequestsAndWaitResponse(this, 1000,receivers, ans, book));
    }
}
