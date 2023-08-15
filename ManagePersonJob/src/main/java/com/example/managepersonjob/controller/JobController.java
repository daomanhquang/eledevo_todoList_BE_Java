package com.example.managepersonjob.controller;


import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.repository.JobRepository;
import com.example.managepersonjob.service.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/job")

public class JobController {
    @Autowired
    private JobServiceImpl jobService;

    @GetMapping("/getPage/{limit}")
    public ResponseEntity<?> getPage(@RequestParam int page, @PathVariable int limit) {
        return new ResponseEntity<>(jobService.getPage(page, limit), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobRequest jobRequest) {
        return new ResponseEntity<>(jobService.add(jobRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody JobRequest jobRequest) {
        return new ResponseEntity<>(jobService.update(id, jobRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/{page}/{limit}")
    public ResponseEntity<?> search(@PathVariable int page, @PathVariable int limit, @RequestParam String textSearch) {
        return new ResponseEntity<>(jobService.search(page, limit, textSearch), HttpStatus.OK);
    }

    @GetMapping("/sort/{page}/{limit}")
    public ResponseEntity<?> sort(@PathVariable int page, @PathVariable int limit, @RequestParam String sortType) {
        return new ResponseEntity<>(jobService.sort(page, limit, sortType), HttpStatus.OK);
    }

    @GetMapping("/sortSearch/{page}/{limit}")
    public ResponseEntity<?> sortSearch(@PathVariable int page, @PathVariable int limit, @RequestParam String sortType, @RequestParam String textSearch) {
        return new ResponseEntity<>(jobService.sortSearch(page, limit, sortType, textSearch), HttpStatus.OK);
    }

}
