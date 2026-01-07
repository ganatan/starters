package com.ganatan.starter.titles;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/titles")
public class TitleController {
  private final TitleService service;

  public TitleController(TitleService service) {
    this.service = service;
  }

  @PostMapping
  public void add(@RequestBody Title t) {
    service.add(t);
  }

  @GetMapping
  public List<Title> list() {
    System.out.println("00000000002");
    return service.list();
  }

  @GetMapping("/{id}")
  public Title get(@PathVariable String id) {
    return service.get(id);
  }
}
