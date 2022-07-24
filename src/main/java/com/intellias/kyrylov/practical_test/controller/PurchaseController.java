package com.intellias.kyrylov.practical_test.controller;

import com.intellias.kyrylov.practical_test.dto.PurchaseDTO;
import com.intellias.kyrylov.practical_test.exception.NotEnoughMoneyException;
import com.intellias.kyrylov.practical_test.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public ResponseEntity<?> makePurchase(@RequestBody PurchaseDTO purchaseDTO) {
        try {
            PurchaseDTO successPurchaseDTO = purchaseService.makePurchase(purchaseDTO);
            return ResponseEntity.ok(successPurchaseDTO);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Not enough money");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
