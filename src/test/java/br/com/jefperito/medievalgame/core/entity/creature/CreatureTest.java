package br.com.jefperito.medievalgame.core.entity.creature;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatureTest {

    private Creature creature;
    private String name = "a name";

    @Before
    public void setUp() {
        creature = Creature.build(name);
    }

    @Test
    public void shouldCreateANewCreatureWithDefaultAttributes() {
        creature.setHealthPoints(100);
        creature.setManaPoints(60);
        creature.setVelocityPoints(7);
        creature.setMagicPoints(5);
        creature.setStrengthPoints(10);

        assertThat(creature.getHealthPoints()).isEqualTo(100);
        assertThat(creature.getManaPoints()).isEqualTo(60);
        assertThat(creature.getVelocityPoints()).isEqualTo(7);
        assertThat(creature.getMagicPoints()).isEqualTo(5);
        assertThat(creature.getStrengthPoints()).isEqualTo(10);
        assertThat(creature.getName()).isEqualTo(name);
    }

    @Test
    public void shouldAddStrengthPoints() {
        creature.setStrengthPoints(10);
        creature.increaseStrengthPoints(2);

        assertThat(creature.getStrengthPoints()).isEqualTo(12);
    }

    @Test
    public void shouldAddVelocityPoints() {
        creature.setVelocityPoints(7);
        creature.increaseVelocityPoints(1);

        assertThat(creature.getVelocityPoints()).isEqualTo(8);
    }

    @Test
    public void shouldAddManaPoints() {
        creature.setManaPoints(60);
        creature.increaseManaPoints(1);

        assertThat(creature.getManaPoints()).isEqualTo(61);
    }

    @Test
    public void shouldAddMagicPoints() {
        creature.setMagicPoints(5);
        creature.increaseMagicPoints(1);

        assertThat(creature.getMagicPoints()).isEqualTo(6);
    }

    @Test
    public void shouldAddHealthPoints() {
        creature.setHealthPoints(100);
        creature.increaseHeathPoints(1);

        assertThat(creature.getHealthPoints()).isEqualTo(101);
    }

    @Test
    public void shouldNotIsAliveIfHealthPointsIsZero() {
        creature.setHealthPoints(0);

        assertThat(creature.isAlive()).isFalse();
    }

    @Test
    public void shouldIsAliveIfHealthPointsIsGreaterThanZero() {
        creature.setHealthPoints(1);

        assertThat(creature.isAlive()).isTrue();
    }

    @Test
    public void shouldDecreaseManaPoint() {
        creature.setManaPoints(1);
        creature.decreaseManaPoints(1);

        assertThat(creature.getManaPoints()).isZero();
    }

    @Test
    public void shouldDecreaseHealthPotion() {
        creature.setHealthPoints(1);
        creature.decreaseHealthPoints(1);

        assertThat(creature.getHealthPoints()).isZero();
    }

    @Test
    public void shouldPowerAttackBeEqualToStrengthPoints() {
        int strengthPoints = 10;
        creature.setStrengthPoints(strengthPoints);

        assertThat(creature.getPowerAttack()).isEqualTo(strengthPoints);
    }
}