package architecture;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static architecture.ArchitectureTestConfig.javaClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class EntrypointTest {

    @Test
    public void controlersShouldBeResideInPackageEntrypoint() {
        ArchRule archRule = classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAnyPackage("..entrypoint..");

        archRule.check(javaClasses);
    }
}
