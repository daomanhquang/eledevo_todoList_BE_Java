package com.example.managepersonjob.controller;

import com.example.managepersonjob.objectRequest.DetailJobRequest;
import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.service.DetailJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/detailJob")
public class DetailJobController {
    @Autowired
    private DetailJobServiceImpl detailJobService;

    @GetMapping("/getPage/{page}/{limit}")
    public ResponseEntity<?> getPage(@PathVariable int page, @PathVariable int limit, @RequestParam Long idJob) {
        return new ResponseEntity<>(detailJobService.getPage(page, limit, idJob), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DetailJobRequest detailJobRequest) {
        return new ResponseEntity<>(detailJobService.add(detailJobRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(detailJobService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DetailJobRequest detailJobRequest) {
        return new ResponseEntity<>(detailJobService.update(id, detailJobRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/{page}/{limit}")
    public ResponseEntity<?> search(@PathVariable int page, @PathVariable int limit, @RequestParam String textSearch,
                                    @RequestParam Long idJob) {
        return new ResponseEntity<>(detailJobService.search(page, limit, textSearch,idJob), HttpStatus.OK);
    }
//
    @GetMapping("/sort/{page}/{limit}")
    public ResponseEntity<?> sort(@PathVariable int page, @PathVariable int limit, @RequestParam String sortType,
                                  @RequestParam Long idJob) {
        return new ResponseEntity<>(detailJobService.sort(page, limit, sortType,idJob), HttpStatus.OK);
    }

    @GetMapping("/sortSearch/{page}/{limit}")
    public ResponseEntity<?> sortSearch(@PathVariable int page, @PathVariable int limit, @RequestParam String sortType,
                                        @RequestParam String textSearch, @RequestParam Long idJob) {
        return new ResponseEntity<>(detailJobService.sortSearch(page, limit, sortType, textSearch,idJob), HttpStatus.OK);
    }


    @GetMapping("/searchTest")
    public ResponseEntity<?> searchTest( @RequestParam String textSearch,
                                    @RequestParam Long idJob) {
        return new ResponseEntity<>(detailJobService.searchTest(textSearch,idJob), HttpStatus.OK);
    }
}
