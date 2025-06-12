import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import cl.maotech.review_service.review_service.model.Review;
import cl.maotech.review_service.review_service.repository.ReviewRepository;

@Component
@Profile("test")
public class DataLoader implements CommandLineRunner{

    @Autowired
    private ReviewRepository reviewRepository;

    public void run(String... args) throws Exception {
        Faker faker = new Random();

        for (Integer = 0; i < 3; i++) {
            Review review = new Review();
            review.setReview(faker.lorem().sentence());
            review.setValue(faker.number().numberBetween(1, 5));
            review.setCourseId(faker.number().numberBetween(1, 10));
            reviewRepository.save(review);
        }
    }

}
