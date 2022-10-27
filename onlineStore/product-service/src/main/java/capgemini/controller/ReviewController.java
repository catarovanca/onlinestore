package capgemini.controller;

import capgemini.dto.ReviewRequest;
import capgemini.dto.ReviewResponse;
import capgemini.exception.ReviewNotFoundException;
import capgemini.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD

    // Add new review
    @PostMapping
    public void register(@RequestBody ReviewRequest reviewRequest){
        reviewService.register(reviewRequest);
    }

    // Update existing review
    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws ReviewNotFoundException {
        reviewService.update(id, fields);
    }



    //Informational Queries
    // Find all reviews
    @GetMapping
    public List<ReviewResponse> findAll() {
        return reviewService.findAll();
    }

    // Find specific review
    @GetMapping(params = {"{id}"})
    public ReviewResponse findbyID(@PathVariable int id) throws ReviewNotFoundException {
        return reviewService.findById(id);
    }

    // Find all comments by rating
    @GetMapping(path = "/ratings", params = {"{rating"})
    public List<ReviewResponse> findByRating(@PathVariable int rating) {
        return reviewService.findByRating(rating);
    }

}