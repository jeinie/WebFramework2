package kr.ac.hansung.controller;

import kr.ac.hansung.model.Offer;
import kr.ac.hansung.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class OfferController {

    // controller -> service -> dao

    @Autowired
    private OfferService offerService;

    @GetMapping("/offers")
    public String showOffers(Model model) {
        List<Offer> offers = offerService.getCurrent();
        model.addAttribute("id_offers", offers);

        return "offers";
    }
}
