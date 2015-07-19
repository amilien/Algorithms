package interviews.amazon.garage;

import java.util.Iterator;

/**
 * The main app controlling the parking garage.
 */
interface ParkingGarage
{
  /**
   * Registers the given garage status listener to receive notifications for
   * changes in the occupied status of a space.
   * @param assigner The GarageStatusListener responsible for issuing spaces.
   */
  void register(GarageStatusListener assigner);

  /**
   * @return the list of spaces in the parking garage. Note: This list may be 
   * very large and take a long time to iterate through.
   */
  Iterator<Space> getSpaces();
}