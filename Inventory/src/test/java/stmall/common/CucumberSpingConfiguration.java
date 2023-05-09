package stmall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import stmall.InventoryApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { InventoryApplication.class })
public class CucumberSpingConfiguration {}
