package architecture;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static architecture.ArchitectureTestConfig.javaClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class UseCaseTest {

    private static final String CORE_USECASE_PACKAGE = "..core.usecase..";

    @Test
    public void shouldBePublic() {
        ArchRule archRule = classes().that().resideInAnyPackage(CORE_USECASE_PACKAGE).should().bePublic();

        archRule.check(javaClasses);
    }

    @Test
    public void dtosShouldBeResideInPackageUseCase() {
        ArchRule archRule = classes()
                .that().haveSimpleNameEndingWith("DTO")
                .should().resideInAnyPackage(CORE_USECASE_PACKAGE);

        archRule.check(javaClasses);
    }
}
