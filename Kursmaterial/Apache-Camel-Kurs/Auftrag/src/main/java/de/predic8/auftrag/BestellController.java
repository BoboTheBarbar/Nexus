package de.predic8.auftrag;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.util.json.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BestellController {

    ProducerTemplate pt;
    ConsumerRoute ct;

    public BestellController(ProducerTemplate pt, ConsumerRoute ct) {
        this.pt = pt;
        this.ct = ct;
    }

    @PostMapping("/auftraege")
    public ResponseEntity beauftragen(@RequestBody JsonObject json) {
        pt.send("direct:autrag-eingang", exchange -> exchange.getMessage().setBody(json));
        return ResponseEntity.ok("Auftrag erhalten");
    }

}
