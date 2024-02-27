package upqnu.library.fruit_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upqnu.library.fruit_test.entity.Fruit;

@Repository
public interface FruitRepository2 extends JpaRepository<Fruit, Long> {

    Fruit findByName(String name);
}
