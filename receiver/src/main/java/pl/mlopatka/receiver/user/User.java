package pl.mlopatka.receiver.user;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Integer age;
    @Column(name = "favourite_movie")
    private String favouriteMovie;
    @Column(name = "favourite_dish")
    private String favouriteDish;
    @Column(name = "creation_time")
    private ZonedDateTime creationTime;
    @Column(name = "last_update_time")
    private ZonedDateTime lastUpdateTime;
}
