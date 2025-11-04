package example2.day107;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Entity는 Service에서만 사용하기에, Controller에서 사용하기 위해서 Dto 생성
public class GoodsDto {
    // DB를 기반으로 작성
    private int gno;
    private int gprice;
    private String create_date;
    private String update_date;
    private String gname;
    private String gdesc;

    // DTO ----------> Entity
    // Controller ---> Service
    public GoodsEntity toEntity(){
        return GoodsEntity.builder()
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .build();
    } // func end
} // class end