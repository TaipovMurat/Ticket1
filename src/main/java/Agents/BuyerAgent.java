package Agents;


import Bahiviours.BuyerRequest;
import Helpers.DFHelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerAgent extends Agent {
    private String neededBook;

    @Override
    protected void setup() {
        log.info("Buyer woke up");
        DFHelper.registerAgent(this, "Buyer");
        addBehaviour(new BuyerRequest());
    }
}
