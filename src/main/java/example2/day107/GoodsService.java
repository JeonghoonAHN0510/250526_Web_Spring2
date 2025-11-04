package example2.day107;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
} // class end