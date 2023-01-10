package Agents;

import Bahiviours.SellerBehaviour;
import Helpers.DFHelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;

@Slf4j
public class SellerAgent extends Agent {
    private SellerCfg books;

    @Override
    protected void setup() {
//        log.info("Seller agent {} was born", this.getLocalName());
        DFHelper.registerAgent(this, "Seller");
        books = getConfigByName(SellerCfg.class, this.getLocalName());
        addBehaviour(new SellerBehaviour(books));
    }

    private <T> T getConfigByName(Class<T> classType, String agentName) {
        try {
            JAXBContext context = JAXBContext.newInstance(classType);
            T cfg = (T) context.createUnmarshaller().unmarshal(new FileReader("src/main/resources/" + agentName + ".xml"));
            return cfg;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
