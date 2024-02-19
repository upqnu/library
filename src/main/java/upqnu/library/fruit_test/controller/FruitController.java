package upqnu.library.fruit_test.controller;

import org.springframework.web.bind.annotation.*;
import upqnu.library.fruit_test.dto.FruitCalculateRequest;
import upqnu.library.fruit_test.dto.FruitCalculateResponse;
import upqnu.library.fruit_test.dto.SumRequest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FruitController {

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
        int add = request.getNum1() + request.getNum2();
        int minus = request.getNum1() - request.getNum2();
        int multiply = request.getNum1() * request.getNum2();
        FruitCalculateResponse response = new FruitCalculateResponse(add, minus, multiply);
        response.setAdd(add);
        response.setMinus(minus);
        response.setMultiply(multiply);
        return response;
    }

    @GetMapping("/api/vi/day-of-the-week")
    public String getDay(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }

    @PostMapping("/api/v1/sumOfNumbers")
    public int getSumOfNumbers(@RequestBody SumRequest request) {
        int sum = 0;
        List<Integer> numbers = request.getNumbers();
        for (int i : numbers) {
            sum += i;
        }

        return sum;
    }

}
