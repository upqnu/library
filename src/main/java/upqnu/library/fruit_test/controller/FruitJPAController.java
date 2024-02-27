package upqnu.library.fruit_test.controller;

import org.springframework.web.bind.annotation.*;
import upqnu.library.fruit_test.dto.*;
import upqnu.library.fruit_test.service.FruitService2;

import java.util.List;

@RestController
public class FruitJPAController {

    private final FruitService2 fruitService2;

    public FruitJPAController(FruitService2 fruitService2) {
        this.fruitService2 = fruitService2;
    }

    @PostMapping("/api/v2/fruit")
    public FruitInfoResponse saveFruitInfo(@RequestBody FruitInfoRequest request) {
        return fruitService2.saveFruitInfo(request);
    }

    @PutMapping("/api/v2/fruit")
    public Long recordFruitSelling(@RequestBody FruitSellingRequest request) {
        fruitService2.recordFruitSelling(request);
        Long id = request.getId();
        return id;
    }

    @GetMapping("/api/v2/fruit/stat")
    public FruitSalesResponse fruitSumResponses(@RequestParam String name) {
        FruitSalesResponse response = fruitService2.fruitSumResponses(name);
        return response;
    }

    @GetMapping("/api/v2/fruit/count")
    public Long countFruit(@RequestParam String name) {
        Long numOfFruit = fruitService2.countFruit(name);
        return numOfFruit;
    }

    @GetMapping("/api/v2/fruit")
    public List<FruitInfoResponse> getFruits() {
        List<FruitInfoResponse> list = fruitService2.getFruits();
        return list;
    }

    @DeleteMapping("/api/v2/fruit")
    public void deleteFruit(@RequestParam Long id, @RequestParam String name) {
        fruitService2.deleteFruit(id, name);
    }

    @GetMapping("/api/v2/fruit/list")
    public List<FruitInfoResponse> getFruitList(@RequestParam String option, @RequestParam Long amount) {
        List<FruitInfoResponse> responses = fruitService2.getFruitList(option, amount);
        return responses;
    }
}
