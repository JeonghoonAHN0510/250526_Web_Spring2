package example.task.task000.model.mapper;

import example.task.task000.model.dto.PhoneDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhoneMapper {
    // [1] 등록
    @Insert("insert into phoneBook ( name, phone, age ) values ( #{name}, #{phone}, #{age} )")
    boolean addList( PhoneDto phoneDto );

    // [2] 전체조회
    @Select("select * from phoneBook")
    List<PhoneDto> getList();

    // [3] 삭제
    @Delete("delete from phoneBook where mno = #{mno}")
    boolean deleteList( int mno );
} // class end