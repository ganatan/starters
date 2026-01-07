package com.ganatan.starter.titles;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class TitleRepository {
  private final List<Title> items = new ArrayList<>();

  public void add(Title t) {
    items.add(t);
  }

  public List<Title> list() {
    Title title = new Title();
    title.setId("00001");
    title.setName("Aliens");
    title.setYear(1984);
    items.add(title);
    return items;
  }

  public Title get(String id) {
    return items.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
  }
}
