package upqnu.library.fruit_test.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import upqnu.library.fruit_test.dto.*;
import upqnu.library.fruit_test.repository.FruitRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public FruitCalculateResponse addTwoNumbers(FruitCalculateRequest request) {
        int add = request.getNum1() + request.getNum2();
        int minus = request.getNum1() - request.getNum2();
        int multiply = request.getNum1() * request.getNum2();
        FruitCalculateResponse response = new FruitCalculateResponse(add, minus, multiply);
        response.setAdd(add);
        response.setMinus(minus);
        response.setMultiply(multiply);
        return response;
    }

    public String getDay(String date) {
        LocalDate localDate = LocalDate.parse(date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public int getSumOfNumbers(SumRequest request) {
        int sum = 0;
        List<Integer> numbers = request.getNumbers();
        for (int i : numbers) {
            sum += i;
        }
        return sum;
    }

    public FruitInfoResponse saveFruitInfo(FruitInfoRequest request) {
        fruitRepository.saveFruitInfo(request.getName(), request.getWarehousingDate(), request.getPrice());
        FruitInfoResponse response = new FruitInfoResponse(request.getName(), request.getWarehousingDate(), request.getPrice());
        return response;
    }

    public void recordFruitSelling(@RequestBody FruitSellingRequest request) {
        if (fruitRepository.findFruit(request.getId())) throw new IllegalArgumentException();
        fruitRepository.findFruitSold(request.getId());
    }

    public FruitSalesResponse fruitSumResponses(String name) {

        Long salesResult = fruitRepository.getSalesResult(name);
        Long notSalesResult = fruitRepository.getNotSalesResult(name);

        return new FruitSalesResponse(salesResult, notSalesResult);
    }
}
