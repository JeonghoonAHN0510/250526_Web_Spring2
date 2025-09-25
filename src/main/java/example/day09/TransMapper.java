package example.day09;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransMapper {
    // 1. Insert1
    @Insert("insert into trans(name) values (#{name})")
    boolean insert1(String name);
    // 2. Insert2 - 비정상ver
    @Insert("insert into trans(name) values (#{name}) error")
    boolean insert2(String name);
    // 3. Update1
    @Update("update trans set money = money + #{money} where name = #{name}")
    boolean deposit( String name, int money );
    // 4. Update2
    @Update("update trans set money = money - #{money} where name = #{name} and money >= #{money}")
    boolean withdraw( String name, int money );
} // interface