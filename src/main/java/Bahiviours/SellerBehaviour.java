package Bahiviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SellerBehaviour extends Behaviour {


    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage receive = getAgent().receive(mt);

        if (mt != null){

        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
