package Agents;


import Behaviours.BuyerRequest;
import Helpers.DFHelper;
import jade.core.AID;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class BuyerAgent extends Agent {
    private String neededBook;
    private int bookNum = new Random().nextInt(6); // Number of book that we are looking for
//    private int bookNum = 1;
    private String book;
    private List<AID> receivers;

    @Override
    protected void setup() {
//        log.info("Buyer woke up");
        DFHelper.registerAgent(this, "Buyer"); // Register agent in DF
        switch (bookNum){ // Buyer choose his needed book
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
    }
}
