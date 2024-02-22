package upqnu.library.fruit_test.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import upqnu.library.fruit_test.dto.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    @PostMapping("/api/v1/fruit")
    public FruitInfoResponse saveFruitInfo(@RequestBody FruitInfoRequest request) {
        String sql = "INSERT into FRUIT (name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());

        FruitInfoResponse response = new FruitInfoResponse(request.getName(), request.getWarehousingDate(), request.getPrice());
        return response;
    }

    @PutMapping("/api/v1/fruit")
    public Long recordFruitSelling(@RequestBody FruitSellingRequest request) {
        String sql = "SELECT * FROM fruit WHERE id = ?";
        boolean inputtedId = jdbcTemplate.query(sql, (rs, rowNum) -> 0, request.getId()).isEmpty();

        if (inputtedId) throw new IllegalArgumentException();

        String sql2 = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql2, request.getId());

        Long id = request.getId();
        return id;
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitSalesResponse fruitSumResponses(@RequestParam String name){
        String saleSql = "select sum(price) from fruit where name = ? and is_sold = 1";
        String notSaleSql = "select sum(price) from fruit where name = ? and is_sold = 0";

        Long salesResult = jdbcTemplate.queryForObject(saleSql, new Object[]{name}, Long.class);
        Long notSalesResult = jdbcTemplate.queryForObject(notSaleSql, new Object[]{name}, Long.class);

        return new FruitSalesResponse(salesResult, notSalesResult);
    }

}
