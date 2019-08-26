package br.com.jefperito.medievalgame.core.entity.creature;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterTest {

    private String name = "An Warrior";
    private int strengthPoints = 1;
    private int velocityPoints = 2;
    private Character anWarrior;

    @Before
    public void setUp() {
        anWarrior = Character.buildWarrior(name, strengthPoints, velocityPoints);
    }

    @Test
    public void shouldBuildAnWarrior() {
        assertThat(anWarrior.getArmor()).isNotNull();
        assertThat(anWarrior.getBoot()).isNotNull();
        assertThat(anWarrior.getHelmet()).isNotNull();
        assertThat(anWarrior.getWeapon()).isNotNull();
        assertThat(anWarrior.getVelocityPoints()).isEqualTo(velocityPoints);
        assertThat(anWarrior.getStrengthPoints()).isEqualTo(strengthPoints);
        assertThat(anWarrior.getName()).isEqualTo(name);
    }

    @Test
    public void shouldCalculatePowerDefense() {
        assertThat(anWarrior.getPowerDefense()).isEqualTo(21.25);
    }

    @Test
    public void shouldCalculatePowerAttack() {
        assertThat(anWarrior.getPowerAttack()).isEqualTo(12.6);
    }
}