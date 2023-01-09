package Agents;


import Bahiviours.BuyerReceive;
import Bahiviours.BuyerRequest;
import Helpers.DFHelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class BuyerAgent extends Agent {
    private String neededBook;
    private int bookNum = new Random().nextInt(5);
    private String book;

    @Override
    protected void setup() {
        log.info("Book number {}", bookNum);
        log.info("Buyer woke up");
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
    }
}
