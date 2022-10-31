package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.CodeCoupon;
import ar.unq.unqnini.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class CouponController {

    @Autowired
    CouponService couponService;

    @PostMapping("/coupon/validate")
        @ResponseBody
        public int applyCoupon(@Validated @RequestBody CodeCoupon couponCode) { return couponService.applyCoupon(couponCode); }
}
