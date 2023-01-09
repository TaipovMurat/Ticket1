package Agents;


import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "cfg")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerCfg {

    @XmlElementWrapper(name = "book")
    @XmlElement(name = "BookName")
    private List<String> bookName;

}
