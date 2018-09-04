package org.litespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationContextV2Test.class,
	BeanDefinitionTest.class,
	BeanDefinitionResolverTest.class
	})
public class AllTests {

}
