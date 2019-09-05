package architecture;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static architecture.ArchitectureTestConfig.javaClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class UseCaseTest {

    @Test
    public void shouldBePublic() {
        ArchRule archRule = classes().that().resideInAnyPackage("..core.usecase..").should().bePublic();

        archRule.check(javaClasses);
    }
}
