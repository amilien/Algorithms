package amazon.garage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

	  public static final void main(String[] args) {
		  SpaceAssigner spaceAssigner = new SpaceAssigner();
		  ParkingGarage parkingGarage = new MyParkingGarage();
		  parkingGarage.register(spaceAssigner);
		  List<Space> spaces = new ArrayList<Space>();
		  Iterator<Space> it = parkingGarage.getSpaces();
		  while (it.hasNext())
			  spaces.add(it.next());
		  Collections.sort(spaces, SpaceAssigner.mComparator);
		  spaceAssigner.initialize(parkingGarage);
		  // add cars in space desirability order
		  for (int i = 0; i < spaces.size(); i++) {
			  Car car = new MyCar();
			  Space space = spaceAssigner.assignSpace(car);
			  // validate cars are added in needed order
			  assert (space == spaces.get(i));
			  spaceAssigner.onSpaceTaken(car, space);
		  }
		  // validate all spaces taken
		  boolean check = true;
		  for (Space space: spaces)
			  check &= space.isOccupied();
		  assert (check);
		  // remove cars in order of desirability
		  for (Space space: spaces) {
			  Car car = space.getOccupyingCar();
			  spaceAssigner.onSpaceFreed(car, space);
		  }
		  // validate all spaces available
		  check = true;
		  for (Space space: spaces)
			  check &= !space.isOccupied();
		  assert (check);
	  }

}
