package example.day12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AxiosDto {
    private String id;
    private String pwd;
    private File file;
} // func end
