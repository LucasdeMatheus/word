package com.myproject.automocao.contrroller;

import com.myproject.automocao.domain.Data;
import com.myproject.automocao.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
public class WordController {
    @Autowired
    private WordService wordService;
    @GetMapping
    public ResponseEntity<?> getWord(@RequestBody Data data){
        return wordService.getWord(data);
    }
}
