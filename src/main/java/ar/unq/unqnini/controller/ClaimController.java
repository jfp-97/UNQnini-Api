package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Claim;
import ar.unq.unqnini.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class ClaimController {

    @Autowired
    ClaimService claimService;

    @PostMapping("/claim/send")
    public void sendClaim(@Valid @RequestBody Claim claim) { claimService.sendClaim(claim); }
}
