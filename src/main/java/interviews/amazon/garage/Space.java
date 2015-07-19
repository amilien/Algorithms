package interviews.amazon.garage;

/**
 * Represents a space in the garage in which a car can park.
 */
interface Space
{
  /**
   * @return A unique identifier for the given space.
   */
  int getID();

  /**
   * @return An integer representing the desirability of the space.
   *         Spaces with higher values are considered more desirable.
   */
  int getDesirability(); 

  /**
   * @return true if the space is currently occupied with a car; 
   *         false, otherwise. This returns the real world state of
   *         the Space.
   */
  boolean isOccupied();

  /**
   * @return the Car that is currently occupying the Space or null
   *         if no Car is currently present. This returns the real
   *         world state of the space.
   */
  Car getOccupyingCar();
}