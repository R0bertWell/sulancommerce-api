package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.models.dtos.SizeDTO;
import src.sulancommerce.services.SizeService;

@RestController
@RequestMapping("/api/v1/sizes")
public class SizeController {
    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("list")
    public ResponseEntity<Page<SizeDTO>> getSizesPaged(@RequestParam(defaultValue =  "") String filter,
                                                       Pageable pageable){
        Page<SizeDTO> sizeDTOS = this.sizeService.getSizesPaged(filter, pageable);
        return new ResponseEntity<>(sizeDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "size/save")
    public ResponseEntity<HttpStatus> saveSize(@RequestBody SizeDTO size) throws Exception {
        this.sizeService.saveSize(size);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "size/{sizeId}/delete")
    public ResponseEntity<HttpStatus> deleteSize(@PathVariable Long sizeId) throws Exception {
        this.sizeService.deleteSize(sizeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
