package example2.day107;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    // 1. 등록
    // { "gprice" : 1000, "gname" : "최초제품명", "gdesc" : "최초제품설명" }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody GoodsDto goodsDto){
        return ResponseEntity.ok(goodsService.save(goodsDto));
    } // func end

    // 2. 전체조회
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(goodsService.findAll());
    } // func end

    // 3. 개별조회
    @GetMapping("/bygno")
    public ResponseEntity<?> findById(@RequestParam int gno){
        return ResponseEntity.ok(goodsService.findById(gno));
    } // func end

    // 4. 수정
    // { "gno" : 1, "gprice" : 1000, "gname" : "수정된제품명", "gdesc" : "수정된제품설명" }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody GoodsDto goodsDto){
        return ResponseEntity.ok(goodsService.update(goodsDto));
    } // func end

    // 5. 개별 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam int gno){
        return ResponseEntity.ok(goodsService.deleteById(gno));
    } // func end
} // class end