package interviews.amazon.garage;

/**
 * Represents a car trying to park in the parking garage.
 */
interface Car
{
  /**
   * @return The state in which the license plate was issued.
   */
  String getLicensePlateState();

  /**
   * @return The license plate number of the car.
   */
  String getLicensePlateNumber();
}