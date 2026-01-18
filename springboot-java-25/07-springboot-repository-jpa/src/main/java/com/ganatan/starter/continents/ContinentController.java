package com.ganatan.starter.continents;

import java.util.List;

import com.ganatan.starter.continents.dto.ContinentRequestDTO;
import com.ganatan.starter.continents.dto.ContinentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/continents")
public class ContinentController {

  private final ContinentService service;

  public ContinentController(ContinentService service) {
    this.service = service;
  }

  @GetMapping
  public List<ContinentResponseDTO> getAllContinents() {
    return service.getAll().stream()
      .map(this::toResponse)
      .toList();
  }

  @GetMapping("/{id}")
  public ContinentResponseDTO getContinentById(@PathVariable int id) {
    return toResponse(service.getById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ContinentResponseDTO createContinent(@Valid @RequestBody ContinentRequestDTO body) {
    return toResponse(service.create(body.name()));
  }

  @PutMapping("/{id}")
  public ContinentResponseDTO updateContinent(
    @PathVariable int id,
    @Valid @RequestBody ContinentRequestDTO body
  ) {
    return toResponse(service.update(id, body.name()));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteContinent(@PathVariable int id) {
    service.delete(id);
  }

  private ContinentResponseDTO toResponse(Continent continent) {
    return new ContinentResponseDTO(
      continent.id(),
      continent.name()
    );
  }

}



//package com.ganatan.starter.continents;
//
//import java.util.List;
//
//import com.ganatan.starter.continents.dto.ContinentRequestDTO;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/continents")
//public class ContinentController {
//
//  private final ContinentService service;
//
//  public ContinentController(ContinentService service) {
//    this.service = service;
//  }
//
//  @GetMapping
//  public List<Continent> getAllContinents() {
//    return service.getAll();
//  }
//
//  @GetMapping("/{id}")
//  public Continent getContinentById(@PathVariable int id) {
//    return service.getById(id);
//  }
//
//  @PostMapping
//  @ResponseStatus(HttpStatus.CREATED)
//  public Continent createContinent(@Valid @RequestBody ContinentRequestDTO body) {
//
//    return service.create(body.name());
//  }
//
//  @PutMapping("/{id}")
//  public Continent updateContinent(
//    @PathVariable int id,
//    @Valid @RequestBody ContinentRequestDTO body
//  ) {
//    return service.update(id, body.name());
//  }
//
//  @DeleteMapping("/{id}")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  public void deleteContinent(@PathVariable int id) {
//    service.delete(id);
//  }
//}
