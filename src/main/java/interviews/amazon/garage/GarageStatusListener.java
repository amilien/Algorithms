package interviews.amazon.garage;

/**
 * An interface used to receive callbacks about changes in the status
 * of Spaces and cars in the garage. Implementers will receive notifications
 * whenever a space becomes occupied or unoccupied and whenever a car 
 * leaves the garage.
 */
interface GarageStatusListener
{
  /**
   * Invoked whenever a car parks in a space.
   * @param car The car parking in the space.
   * @param space The space being occupied.
   */
  void onSpaceTaken(Car car, Space space);

  /**
   * Invoked whenever a car leaves a space.
   * @param car The car leaving the space.
   * @param space The space that the car left.
   */
  void onSpaceFreed(Car car, Space space);
  
  /**
   * Invoked whenever a car leaves the garage.
   * @param car The car leaving the garage.
   */
  void onGarageExit(Car car);
}