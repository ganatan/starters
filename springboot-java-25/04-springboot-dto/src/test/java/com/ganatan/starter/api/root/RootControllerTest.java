package com.ganatan.starter.api.root;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class RootControllerTest {

  @Test
  void root_shouldReturnAMap() {
    RootController controller = new RootController();
    Map<String, Object> result = controller.rootWithMapOf();

    assertNotNull(result);
    assertEquals("springboot-starter", result.get("application"));
    assertEquals("running", result.get("status"));
    assertTrue(result.containsKey("java"));
    assertNotNull(result.get("java"));
  }

  @Test
  void root_shouldContainThreeKeys() {
    RootController controller = new RootController();
    Map<String, Object> result = controller.rootWithMapOf();

    assertEquals(3, result.size());
  }

  @Test
  void root_shouldReturnCurrentJavaVersion() {
    RootController controller = new RootController();
    Map<String, Object> result = controller.rootWithMapOf();

    assertEquals(System.getProperty("java.version"), result.get("java"));
  }
}
