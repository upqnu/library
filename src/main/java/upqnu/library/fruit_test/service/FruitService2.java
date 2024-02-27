package upqnu.library.fruit_test.service;

import org.springframework.stereotype.Service;
import upqnu.library.fruit_test.dto.FruitInfoRequest;
import upqnu.library.fruit_test.dto.FruitInfoResponse;
import upqnu.library.fruit_test.dto.FruitSalesResponse;
import upqnu.library.fruit_test.dto.FruitSellingRequest;
import upqnu.library.fruit_test.entity.Fruit;
import upqnu.library.fruit_test.repository.FruitRepository2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService2 {

    private final FruitRepository2 fruitRepository2;

    public FruitService2(FruitRepository2 fruitRepository2) {
        this.fruitRepository2 = fruitRepository2;
    }

    public FruitInfoResponse saveFruitInfo(FruitInfoRequest request) {
        fruitRepository2.save(new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice()));
        FruitInfoResponse response = new FruitInfoResponse(request.getName(), request.getWarehousingDate(), request.getPrice());
        return response;
    }

    public List<FruitInfoResponse> getFruits() {
        return fruitRepository2.findAll().stream()
                .map(FruitInfoResponse::new)
                .collect(Collectors.toList());
    }

    public void recordFruitSelling(FruitSellingRequest request) {
        Fruit fruit = fruitRepository2.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        fruit.updateSellingState(true);
        fruitRepository2.save(fruit);
    }

    public FruitSalesResponse fruitSumResponses(String name) {
        List<Fruit> allFruits = fruitRepository2.findAll();
        List<Fruit> theFruit = new ArrayList<>();
        for (Fruit f : allFruits) {
            if (f.getName().equals(name)) theFruit.add(f);
        }

        if (theFruit.isEmpty()) throw new IllegalArgumentException();

        Long salesResult = 0L;
        Long notSalesResult = 0L;
        for (Fruit f : theFruit) {
            if (f.isSold()) salesResult += f.getPrice();
            else notSalesResult += f.getPrice();
        }

        FruitSalesResponse response = new FruitSalesResponse(salesResult, notSalesResult);
        return response;
    }

    public void deleteFruit(Long id, String name) {
        Fruit fruit = fruitRepository2.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        if (fruit.getName().equals(name)) {
            fruitRepository2.delete(fruit);
        } else {
            throw new IllegalArgumentException("입력하신 과일 이름과 DB에 저장된 과일 이름이 다릅니다.");
        }
    }

    public Long countFruit(String name) {
        List<Fruit> allFruits = fruitRepository2.findAll();
        List<Fruit> theFruit = new ArrayList<>();
        for (Fruit f : allFruits) {
            if (f.getName().equals(name)) theFruit.add(f);
        }

        if (theFruit.isEmpty()) throw new IllegalArgumentException();

        Long countOfFruitSold = 0L;
        for (Fruit f : theFruit) {
            if (f.isSold()) countOfFruitSold += 1L;
        }

        return countOfFruitSold;
    }

    public List<FruitInfoResponse> getFruitList(String option, Long amount) {
        List<Fruit> allFruits = fruitRepository2.findAll();
        FruitInfoResponse response;
        List<FruitInfoResponse> selectedFruits = new ArrayList<>();
        for (Fruit f : allFruits) {
            if (option.equals("GTE")) {
                if (!f.isSold() && f.getPrice() >= amount) {
                    response = new FruitInfoResponse(f);
                    selectedFruits.add(response);
                }
            }
            else if (option.equals("LTE")) {
                if (!f.isSold() && f.getPrice() <= amount) {
                    response = new FruitInfoResponse(f);
                    selectedFruits.add(response);
                }
            }
            else throw new IllegalArgumentException();
        }

        return selectedFruits;
    }
}
