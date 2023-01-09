package Agents;


import Bahiviours.BuyerReceive;
import Bahiviours.BuyerRequest;
import Helpers.DFHelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerAgent extends Agent {
    private String neededBook;
    private int bookNum = (int) Math.random() * 4;
    private String book;

    @Override
    protected void setup() {
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
        }
        log.info("Buyer looking for {}", book);
        addBehaviour(new BuyerRequest(book));
        addBehaviour(new BuyerReceive(book));
    }
}
