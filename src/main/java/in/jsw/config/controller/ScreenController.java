package in.jsw.config.controller;

import in.jsw.config.model.Role;
import in.jsw.config.model.Screen;
import in.jsw.config.service.ScreenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/screen")
public class ScreenController {

    private final ScreenService screenService;


    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @PostMapping
    public ResponseEntity<Screen> addScreen(@RequestBody Screen screen) {
        Screen screenObj = screenService.create(screen);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("screens", "/screens" + screenObj.getId().toString());
        return new ResponseEntity<>(screenObj, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<Screen>> getAllScreens() throws InterruptedException{
        List<Screen> screensList = screenService.findAll();
        return new ResponseEntity<>(screensList, HttpStatus.OK);
    }
}
