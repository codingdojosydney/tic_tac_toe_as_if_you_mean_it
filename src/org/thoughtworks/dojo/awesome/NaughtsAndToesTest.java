package org.thoughtworks.dojo.awesome;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class NaughtsAndToesTest {
	private Game game;
	private Square square;

	@Before
	public void setupTheGame() {
		this.game = new Game();
		this.square = new Square();
	}
	
	@Test
	public void gameShouldBeOverWhenAllFieldsAreTaken() {
		game.allFieldsTaken = true;
		assertTrue(game.isOver());
	}

	@Test
	public void gameShouldNotBeOverIfThereAreEmptyFields() {
		game.allFieldsTaken = false;
		assertFalse(game.isOver());
	}
	
	@Test
	public void gameShouldBeOverIfAllTheSquaresInARowAreTakenByTheSamePlayer() {
		game.allSquaresOnARowAreTakenBySamePlayer = true;
		assertTrue(game.isOver());
	}
	
	@Test
	public void gameShouldBeOverIfAllTheSquaresInAColumnAreTakenBytheSamePlayer() {
		game.allSquaresOnAColumnAreTakenBySamePlayer = true;		
		assertTrue(game.isOver());
	}
	
	@Test
	public void gameShouldBeOverIfOnePlayerCapturesADiagonal() {
		game.allSquaresOnADiagonalAreTakenBySamePlayer = true;
		assertTrue(game.isOver());
	}
	
	@Test
	public void playerCanTakeAFieldIfNotAlreadyTaken() {
		assertTrue(square.canIGoHere());
	}

	@Test
	public void afterBeingTakenASquareIsNotGoable() {
		square.take();
		assertFalse(square.canIGoHere());
	}

	@Test
	public void playerCannotTakeAFieldIfItIsAlreadyTaken() {
		square.take();
		assertFalse(square.canIGoHere());
	}

	class Square {
		private boolean squareTaken;
		public Square() {
			squareTaken = false;
		}
		
		boolean canIGoHere() {
			return !squareTaken;
		}

		void take() {
			squareTaken = true;
		}
	}

	class Game {
		public boolean allSquaresOnADiagonalAreTakenBySamePlayer;
		public boolean allSquaresOnAColumnAreTakenBySamePlayer;
		public boolean allFieldsTaken;
		public boolean allSquaresOnARowAreTakenBySamePlayer;

		boolean areAllFieldsTaken() {
			return allFieldsTaken;
		}

		boolean areThereEmptyFields() {
			return !allFieldsTaken;
		}

		boolean areAllSquaresOnOneRowTakenByOnePlayer() {
			return allSquaresOnARowAreTakenBySamePlayer;
		}

		boolean isOver() {
			return areAllSquaresOnOneRowTakenByOnePlayer() || areAllFieldsTaken() || allSquaresOnADiagonalAreTakenBySamePlayer()|| allSquaresOnAColumnAreTakenBySamePlayer();
		}

		private boolean allSquaresOnAColumnAreTakenBySamePlayer() {
			return allSquaresOnAColumnAreTakenBySamePlayer;
		}

		private boolean allSquaresOnADiagonalAreTakenBySamePlayer() {
			return allSquaresOnADiagonalAreTakenBySamePlayer;
		}
		
	}
}
