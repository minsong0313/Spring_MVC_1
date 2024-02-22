package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//상품 저장소
@Repository
public class ItemRepository {

    //static
    private static final Map<Long, Item> store = new HashMap<>(); //동시에 여러 쓰레드가 접근 -> 해쉬맵 쓰면 안됨 => ConcurrentHashMap 사용해야함
    private static long sequence = 0L;

    //상품 등록
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    //상품 목록
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //상품 수정
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }









}
