package pl.mlopatka.receiver.user;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomUserDataCreator implements UserDataCreator {

    private static final List<String> FAVOURITE_DISHES = List.of(
            "lasagne", "chicken soup", "beef steak", "spring salad", "tomato soup");

    private static final List<String> FAVOURITE_MOVIE = List.of(
        "batman", "superman", "spiderman", "uncharted", "last samurai"
    );

    @Override
    public void updateUserData(User user) {
        user.setFavouriteDish(getRandomElement(FAVOURITE_DISHES));
        user.setFavouriteMovie(getRandomElement(FAVOURITE_MOVIE));
        user.setAge(ThreadLocalRandom.current().nextInt(18,60));
        user.setLastUpdateTime(ZonedDateTime.now());
    }

    private <T> T getRandomElement(List<T> collection) {
        int generatedNr = ThreadLocalRandom.current().nextInt(0,collection.size());
        return collection.get(generatedNr);
    }

}
