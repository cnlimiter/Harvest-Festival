package joshie.harvest.api.animals;

import javax.annotation.Nonnull;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IAnimalType {
	/**
	 * Return a simple name for this animal type
	 **/
	String getName();

	/**
	 * Return a stack to represent this animal in the relationships panel
	 **/
	@Nonnull
	ItemStack getIcon();

	/**
	 * @return an array of food type this animal can consume
	 **/
	AnimalFoodType[] getFoodTypes();

	/**
	 * @return the minimum lifespan for this animal type
	 **/
	int getMinLifespan();

	/**
	 * @return the maximum lifespan for this animal type
	 **/
	int getMaxLifespan();

	/**
	 * @return the number of days between producing products
	 **/
	int getDaysBetweenProduction();

	/**
	 * @return how many generic treats
	 * this animal needs to up it's productivity
	 **/
	int getGenericTreatCount();

	/**
	 * @return how many typed treats
	 * this animal needs to up it's productivity
	 **/
	int getTypeTreatCount();

	/**
	 * The relationship bonus based on the action
	 *
	 * @param action the action
	 * @return the points awarded
	 **/
	int getRelationshipBonus(AnimalAction action);

	/**
	 * @param stats the animals stats
	 * @return the product that this animal produces
	 **/
	@Nonnull
	ItemStack getProduct(AnimalStats stats);

	/**
	 * @param stats the animals stats
	 * @return a list of stacks to use for display purposes of what this animal can produce
	 **/
	NonNullList<ItemStack> getProductsForDisplay(AnimalStats stats);

	/**
	 * Called whenever an animal is reset to being able to produce again
	 *
	 * @param stats  the stats
	 * @param entity the animal
	 **/
	void refreshProduct(AnimalStats stats, EntityAnimal entity);
}