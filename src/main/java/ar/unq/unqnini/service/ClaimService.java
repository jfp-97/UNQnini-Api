package ar.unq.unqnini.service;
import ar.unq.unqnini.model.Claim;
import ar.unq.unqnini.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    public void sendClaim(Claim claim) { claimRepository.save(claim); }
}
