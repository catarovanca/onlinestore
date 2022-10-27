package capgemini.service;

import capgemini.dto.ReviewRequest;
import capgemini.dto.ReviewResponse;
import capgemini.exception.ReviewNotFoundException;
import capgemini.model.Review;
import capgemini.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private ReviewResponse convertEntitytoDTO(Review review){
        ReviewResponse reviewResponse = modelMapper.map(review, ReviewResponse.class);
        return reviewResponse;
    }

    private List<ReviewResponse> convertEntitytoDTO(List<Review> review){
        return review.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Review convertDTOtoEntity(ReviewRequest reviewRequest){
        Review review = modelMapper.map(reviewRequest, Review.class);
        return review;
    }

    private Review convertDTOtoEntity(ReviewResponse reviewResponse){
        Review review = modelMapper.map(reviewResponse, Review.class);
        return review;
    }


    //CRUD Operations

    public void register(ReviewRequest reviewRequest) {
        Review review = convertDTOtoEntity(reviewRequest);
        reviewRepository.save(review);
    }

    public void update(int id, Map<Object, Object> fields) throws ReviewNotFoundException {
        ReviewResponse existingReview = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(ReviewResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingReview, value);
        });
        Review review = convertDTOtoEntity(existingReview);
        reviewRepository.save(review);
    }

    public void delete(int id) {
        reviewRepository.deleteById(id);
    }


    //Informational Queries

    public List<ReviewResponse> findAll(){
        List<Review> review = reviewRepository.findAll();
        return convertEntitytoDTO(review);
    }

    public List<ReviewResponse> findByRating(int rating) {
       List<Review> review = reviewRepository.findByRating(rating);
        return convertEntitytoDTO(review);
    }

    public ReviewResponse findById(int id) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("Review with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(review);
    }


}


