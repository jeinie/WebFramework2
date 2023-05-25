package kr.ac.hansung.controller;

import kr.ac.hansung.exception.OfferNotFoundException;
import kr.ac.hansung.model.ErrorResponse;
import kr.ac.hansung.model.Offer;
import kr.ac.hansung.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/offers")
public class OfferRestController {
    @Autowired
    private OfferService offerService;

    @GetMapping
    public ResponseEntity<List<Offer>> getOffers() {
        List<Offer> offers = offerService.getOffers();
        if (offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // (header, body,) status
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOffer(@PathVariable("id") int id) {
        Offer offer = offerService.getOfferById(id);
        if (offer == null)
            throw new OfferNotFoundException(id);

        return new ResponseEntity<>(offer, HttpStatus.OK); // (header,) body, status
    }

    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody Offer offer, UriComponentsBuilder ucBuilder) {
        offerService.insertOffer(offer);

        // 새롭게 만들어진 offer 에 접근하려면, 어떤 uri 로 접근해야 하는지 header 에 담아서 클라이언트에 전달
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/offers/{id}").buildAndExpand(offer.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED); // header, (body), status
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable("id") int id, @RequestBody Offer offer) {
        Offer currentOffer = offerService.getOfferById(id);
        if (currentOffer == null)
            throw new OfferNotFoundException(id);

        currentOffer.setEmail(offer.getEmail());
        currentOffer.setName(offer.getName());
        currentOffer.setText(offer.getText());

        offerService.updateOffer(currentOffer);

        return new ResponseEntity<>(currentOffer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") int id) {
        Offer currentOffer = offerService.getOfferById(id);
        if (currentOffer == null)
            throw new OfferNotFoundException(id);

        offerService.deleteOffer(currentOffer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
