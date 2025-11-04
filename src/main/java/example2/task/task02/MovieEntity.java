package example2.task.task02;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Movie_Entity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;    // 영화번호
    private String title;   // 영화제목
    private String director;// 영화감독
    private String releaseDate; // 개봉일
    private double rating;  // 평점

    public MovieDto toDto(){
        return MovieDto.builder()
                .movie_id(this.movieId)
                .title(this.title)
                .director(this.director)
                .release_date(this.releaseDate)
                .rating(this.rating)
                .create_date(this.getCreateDate().toString())
                .update_date(this.getUpdateDate().toString())
                .build();
    } // func end
} // class end