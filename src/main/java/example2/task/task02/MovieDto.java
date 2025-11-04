package example2.task.task02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private int movie_id;
    private String director;
    private String create_date;
    private String update_date;
    private String release_date;
    private String title;
    private double rating;

    public MovieEntity toEntity(){
        return MovieEntity.builder()
                .movieId(this.movie_id)
                .title(this.title)
                .director(this.director)
                .releaseDate(this.release_date)
                .rating(this.rating)
                .build();
    } // func end
} // class end