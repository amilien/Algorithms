package interviews.amazon.garage;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * The SpaceAssigner is responsible for assigning a space for an incoming
 * car to park in. This is done by calling the assignSpace() API.
 *
 * The SpaceAssigner responds to changes in space availability by 
 * implementing the GarageStatusListener interface.
 * 
 * Additional assumptions:
 * 1. As no package was declared in the task description, all interfaces and classes
 * were implemented in one file (should be fixed in real project)
 * 2. When a space was freed, the car will not be using it again (the car left the garage 
 * and must reenter to get another space assigned)
 * 3. No two cars will have the same license plate (no checking code for that)
 * 4. It is designed for a single thread usage
 * 5. ParkingGarage class is supplied (as task doesn't say to implement it)
 * 6. contains main() method with tests, to be removed in production
 */
public class SpaceAssigner implements GarageStatusListener {

	// Spaces provided by the garage, sorted by occupation and desirability.
	// Allows the spaces to maintain their ordering based on desirability and occupation.
	private Collection<MySpace> mSpaces = new TreeSet<MySpace>(mComparator);

	// list of spaces assigned
	private Collection<MySpace> mAssignedSpaces;

	// places most desirable spaces on top of collection.
	// if space is occupied, place it at the end of collection.
	static final Comparator<Space> mComparator = new Comparator<Space>() {
		@Override
		public int compare(Space s1, Space s2) {
			int first = s1.isOccupied() ? - s1.getDesirability() : s1.getDesirability();
			int second = s2.isOccupied() ? - s2.getDesirability() : s2.getDesirability();
			return first < second ? -1 : (first == second ? 0 : 1);
		}
	};

 /**
   * Initiates the SpaceAssigner. This method is called only once per
   * app start-up.
   * @param garage The parking garage for which you are vending spaces.
   *
   * Runtime: O(n) (n is the number of spaces in the garage)
   * Space: O(n) (n is the number of spaces in the garage)
   */
  public void initialize(ParkingGarage garage) {
	  // register SpaceAssigner with garage
	  garage.register(this);
	  // init spaces associated with garage
	  final Iterator<Space> it = garage.getSpaces();
	  while (it.hasNext())
		  mSpaces.add(new MySpace(it.next()));
	  // init list of assigned spaces
	  mAssignedSpaces = new HashSet<MySpace>(mSpaces.size());
  }

  /**
   * Assigns a space to an incoming car and returns that space.
   * 
   * @param car The incoming car that needs a space.
   * @returns The space reserved for the incoming car.
   *
   * Runtime: O(n) (n is the number of spaces in the garage)
   * Space: O(1)
   */
  public Space assignSpace(Car car) {
	  MySpace assignedSpace = null;
	  // if spaces are available
	  if (mAssignedSpaces.size() < mSpaces.size()) {
		  Iterator<MySpace> it = mSpaces.iterator();
		  while (it.hasNext()) {
			  MySpace space = it.next();
			  if (!space.isOccupied()) {
				  // if the space was assigned but the car not occupying the space
				  if (!mAssignedSpaces.contains(space)) {
					  assignedSpace = space;
					  mAssignedSpaces.add(assignedSpace);
					  break;
				  }
			  }
		  }
	  }
	  return assignedSpace;
  }

  /**
   * {@inheritDoc}
   *
   * Runtime: O(n) (n is the number of spaces in the garage)
   * Space: O(1) (as no new allocations)
   */
  public void onSpaceTaken(Car car, Space space) {
	  MySpace mySpace = getMySpace(space);
	  // assume that space always exists
	  mySpace.setOccupyingCar(car);
  }

  /**
   * {@inheritDoc}
   *
   * Runime: O(n) (n is the number of spaces in the garage)
   * Space: O(1) (as no new allocations)
   */
  public void onSpaceFreed(Car car, Space space) {
	  MySpace mySpace = getMySpace(space);
	  // assume that space always exists
	  mySpace.setOccupyingCar(null);
	  // assume car cannot reuse the space so reassign
	  mAssignedSpaces.remove(mySpace);
  }
  
  /**
   * {@inheritDoc}
   * 
   * No need to implement as necessary freeing is 
   * done in onSpaceFreed method
   */
  public void onGarageExit(Car car) {
  }
  
  /**
   * Returns the MySpace associated with the passed Space.
   * 
   * @param space The space to use as a key
   * @return the MySpace associated with the passed Space.
   * Should not be null unless the space was not assigned.
   */
  private MySpace getMySpace(Space space) {
	  MySpace space1 = null;
	  Iterator<MySpace> it = mAssignedSpaces.iterator();
	  while (it.hasNext()) {
		  MySpace space2 = it.next();
		  if (space2.equals(space)) {
			  space1 = space2;
			  break;
		  }
	  }
	  return space1;
  }

}

