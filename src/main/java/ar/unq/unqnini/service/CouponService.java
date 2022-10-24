package ar.unq.unqnini.service;
import ar.unq.unqnini.model.CodeCoupon;
import ar.unq.unqnini.model.Coupon;
import ar.unq.unqnini.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    @Autowired
    CouponRepository couponRepository;

    public int applyCoupon(CodeCoupon couponName) {
        Optional<Coupon> coupon = couponRepository.findById(couponName.getCodename());
        if(coupon.isEmpty()) {
            throw new RuntimeException("[" + couponName.getCodename() + "] no es un cupon valido.");
        } else {
            return coupon.get().getPercentage();
        }
    }
}
