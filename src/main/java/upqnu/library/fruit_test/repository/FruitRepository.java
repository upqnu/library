package upqnu.library.fruit_test.repository;

import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface FruitRepository {

    public void saveFruitInfo(String name, LocalDate warehousingDate, Long price);
    public boolean findFruit(Long id);
    public void findFruitSold(Long id);
    public Long getSalesResult(String name);
    public Long getNotSalesResult(String name);
}
