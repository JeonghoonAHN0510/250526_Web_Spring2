package example2.day107;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    // 1. 등록
    public GoodsDto save(GoodsDto goodsDto){
        // 1-1. 저장할 Dto를 Entity로 변환
        GoodsEntity goodsEntity = goodsDto.toEntity();
        // 1-2. 저장한 Entity를 .save()를 통해 저장
        GoodsEntity savedEntity = goodsRepository.save(goodsEntity);
        // 1-3. PK가 생성되었으면, 생성된 Entity 반환
        if (savedEntity.getGno() > 0) return savedEntity.toDto();
        // 1-4. 실패했으면, null 반환
        return null;
    } // func end

    // 2. 전체조회
    public List<GoodsDto> findAll(){
        // 2-1. .findAll()을 통해 전체 Entity를 조회
        List<GoodsEntity> goodsEntityList = goodsRepository.findAll();
        // 2-2. 모든 Entity를 Dto로 변환
        // 방법1) for 반복문
        List<GoodsDto> forList = new ArrayList<>();
        for (GoodsEntity goodsEntity : goodsEntityList){
            forList.add(goodsEntity.toDto());
        } // for end
        // return forList;
        // 방법2) Stream API - day88·89
        List<GoodsDto> streamList = goodsEntityList
                .stream().map(GoodsEntity::toDto)   // Entity 하나씩 toDto 메소드 호출
                .collect(Collectors.toList());      // List로 변환
        return streamList;
    } // func end

    // 3. 개별조회
    public GoodsDto findById(int gno){
        // 3-1. gno를 통해 개별조회 진행
        Optional<GoodsEntity> optional = goodsRepository.findById(gno);
        // 3-2. 반환값이 존재하면
        if (optional.isPresent()){
            // 3-3. Entity를 꺼내서 Dto로 변환하여 반환
            GoodsEntity goodsEntity = optional.get();
            return goodsEntity.toDto();
        } else {
            // 3-4. 반환값이 없으면 null 반환
            return null;
        } // if end
    } // func end

    // 4. 수정
    public GoodsDto update(GoodsDto goodsDto){
        // 4-1. 수정할 Entity 조회
        Optional<GoodsEntity> optional = goodsRepository.findById(goodsDto.getGno());
        // 4-2. optional이 존재하면
        if (optional.isPresent()){
            // 4-3. Entity를 꺼내서
            GoodsEntity goodsEntity = optional.get();
            // 4-4. setter를 통해 수정 진행
            goodsEntity.setGname(goodsDto.getGname());
            goodsEntity.setGprice(goodsDto.getGprice());
            goodsEntity.setGdesc(goodsDto.getGdesc());
            // 4-5. 수정된 Entity를 Dto로 반환
            return goodsEntity.toDto();
        } // if end
        // 4-6. optional이 없으면 null 반환
        return null;
    } // func end

    // 5. 개별 삭제
    public boolean deleteById(int gno){
        // .existById(PK) : PK에 해당하는 행이 존재하면 true, 없으면 false
        // 5-1. 존재한다면
        if (goodsRepository.existsById(gno)){
            // 5-2. 삭제를 하고 true 반환
            goodsRepository.deleteById(gno);
            return true;
        } else {
            // 5-3. 존재하지 않으면, false 반환
            return false;
        } // if end
    } // func end
} // class end