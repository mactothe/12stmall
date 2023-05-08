package stmall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import stmall.CostomerCenterApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { CostomerCenterApplication.class })
public class CucumberSpingConfiguration {}
