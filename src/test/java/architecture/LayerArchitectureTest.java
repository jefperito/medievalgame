package architecture;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayerArchitectureTest {

    @Test
    public void shouldCheckThatApplicationLayerMustBeVisibleOnlyOwnLayer() {
        ArchRule archRule = layeredArchitecture()
                .layer("EntryPoint").definedBy("..application.entrypoint..")
                .layer("Repository").definedBy("..application.repository..")
                .layer("Entity").definedBy("..core.entity..")
                .layer("UseCase").definedBy("..core.usecase..")
                .layer("Engine").definedBy("..core.engine..")

                .whereLayer("EntryPoint").mayOnlyBeAccessedByLayers()
                .whereLayer("Entity").mayOnlyBeAccessedByLayers("Entity", "UseCase", "Engine", "Repository")
                .whereLayer("Engine").mayOnlyBeAccessedByLayers("UseCase", "EntryPoint")
                .whereLayer("UseCase").mayOnlyBeAccessedByLayers("UseCase", "EntryPoint", "Engine", "Repository");

        archRule.check(ArchitectureTestConfig.javaClasses);
    }
}
