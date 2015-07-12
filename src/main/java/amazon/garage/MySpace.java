package amazon.garage;

class MySpace implements Space {

	private Car mOccupyingCar;
	private Space mSpace;
	
	public MySpace(Space space) {
		mSpace = space;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getID() {
		return mSpace.getID();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getDesirability() {
		return mSpace.getDesirability();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isOccupied() {
		return (getOccupyingCar() != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public Car getOccupyingCar() {
		return mOccupyingCar;
	}

	/**
	 * Set the object of the car occupying the parking space.
	 * 
	 * @param occupyingCar
	 * object of the car occupying the space
	 */
	public void setOccupyingCar(Car occupyingCar) {
		mOccupyingCar = occupyingCar;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Space)
			return (((Space) obj).getID() == getID());
		else
			return false;
	}

	@Override
	public int hashCode() {
		// assumption is that getID is unique, therefore using it as hashCode
		return getID();
	}

}
