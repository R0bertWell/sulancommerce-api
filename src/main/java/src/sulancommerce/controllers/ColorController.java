package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.services.ColorService;

@RestController
@RequestMapping("/api/v1/colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("list")
    public ResponseEntity<Page<ColorDTO>> getColorsPaged(@RequestParam(defaultValue =  "") String filter,
                                                         Pageable pageable){
        Page<ColorDTO> colorDTOS = this.colorService.getColorsPaged(filter, pageable);
        return new ResponseEntity<>(colorDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "color/save")
    public ResponseEntity<HttpStatus> saveColor(@RequestBody ColorDTO color) throws Exception {
        this.colorService.saveColor(color);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "color/{colorId}/delete")
    public ResponseEntity<HttpStatus> deleteColor(@PathVariable Long colorId) throws Exception {
        this.colorService.deleteColor(colorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
