package upqnu.library.fruit_test.controller;

import org.springframework.web.bind.annotation.*;
import upqnu.library.fruit_test.dto.*;
import upqnu.library.fruit_test.service.FruitService;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

//    @GetMapping("/api/v1/calc")
//    public FruitCalculateResponse addTwoNumbers(
//            @RequestParam("num1") int num1,
//            @RequestParam("num2") int num2) {
//
//        int add = num1 + num2;
//        int minus = num1 - num2;
//        int multiply = num1 * num2;
//        FruitCalculateResponse response = new FruitCalculateResponse(add, minus, multiply);
//        response.setAdd(add);
//        response.setMinus(minus);
//        response.setMultiply(multiply);
//        return response;
//    }

    @GetMapping("/api/v1/calc")
    public FruitCalculateResponse addTwoNumbers(
            FruitCalculateRequest request) {
        FruitCalculateResponse response = fruitService.addTwoNumbers(request);
        return response;
    }

    @GetMapping("/api/vi/day-of-the-week")
    public String getDay(@RequestParam("date") String date) {
        String dayOfWeek = fruitService.getDay(date);
        return dayOfWeek;
    }

    @PostMapping("/api/v1/sumOfNumbers")
    public int getSumOfNumbers(@RequestBody SumRequest request) {
        int sum = fruitService.getSumOfNumbers(request);
        return sum;
    }

    @PostMapping("/api/v1/fruit")
    public FruitInfoResponse saveFruitInfo(@RequestBody FruitInfoRequest request) {
        return fruitService.saveFruitInfo(request);
    }

    @PutMapping("/api/v1/fruit")
    public Long recordFruitSelling(@RequestBody FruitSellingRequest request) {
        fruitService.recordFruitSelling(request);
        Long id = request.getId();
        return id;
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitSalesResponse fruitSumResponses(@RequestParam String name) {
        FruitSalesResponse response = fruitService.fruitSumResponses(name);
        return response;
    }

}
