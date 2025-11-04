package example2.day107;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
} // class end