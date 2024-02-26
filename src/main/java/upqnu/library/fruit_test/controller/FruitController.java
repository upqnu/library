package upqnu.library.fruit_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import upqnu.library.fruit_test.dto.*;
import upqnu.library.fruit_test.service.FruitService;

@RestController
public class FruitController {

//    private final JdbcTemplate jdbcTemplate;
    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
//        this.jdbcTemplate = jdbcTemplate;
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
//        int add = request.getNum1() + request.getNum2();
//        int minus = request.getNum1() - request.getNum2();
//        int multiply = request.getNum1() * request.getNum2();
//        FruitCalculateResponse response = new FruitCalculateResponse(add, minus, multiply);
//        response.setAdd(add);
//        response.setMinus(minus);
//        response.setMultiply(multiply);
        return response;
    }

    @GetMapping("/api/vi/day-of-the-week")
    public String getDay(@RequestParam("date") String date) {
        String dayOfWeek = fruitService.getDay(date);
//        LocalDate localDate = LocalDate.parse(date);
//        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek;
    }

    @PostMapping("/api/v1/sumOfNumbers")
    public int getSumOfNumbers(@RequestBody SumRequest request) {
        int sum = fruitService.getSumOfNumbers(request);
//        int sum = 0;
//        List<Integer> numbers = request.getNumbers();
//        for (int i : numbers) {
//            sum += i;
//        }

        return sum;
    }

    @PostMapping("/api/v1/fruit")
    public FruitInfoResponse saveFruitInfo(@RequestBody FruitInfoRequest request) {
//        String sql = "INSERT into FRUIT (name, warehousingDate, price) values (?, ?, ?)";
//        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
//
//        FruitInfoResponse response = new FruitInfoResponse(request.getName(), request.getWarehousingDate(), request.getPrice());
//        FruitInfoResponse response = fruitService.saveFruitInfo(request);
//        return response;
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
