package br.com.seniorsolution.estagiario.util;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.seniorsolution.estagiario.Application;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({ "br.com.seniorsolution.estagiario" }) 
public class IntegrationTestCommon {

   @BeforeClass
   public static void init() {
	   FixtureFactoryLoader.loadTemplates("br.com.seniorsolution.estagiario.template");
   }
}