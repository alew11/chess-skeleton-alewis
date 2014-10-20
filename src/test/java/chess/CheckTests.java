package chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckTests {

	private GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	/*
	 * Move pieces till in Fool's Mate checkmate position
	 */
	@Test
	public void testFoolsMate() {
		
		state.reset();
		
		state.movePiece(new Position("f2"), new Position("f3"));
		state.movePiece(new Position("e7"), new Position("e5"));
		state.movePiece(new Position("g2"), new Position("g4"));
		state.movePiece(new Position("d8"), new Position("h4"));
		
		assertEquals(0, state.checkForMate());
	}
	
	@Test
	public void testCheck() {
		
		state.reset();
		
		state.movePiece(new Position("e2"), new Position("e4"));
		state.movePiece(new Position("e7"), new Position("e5"));
		state.movePiece(new Position("d1"), new Position("h5"));
		state.movePiece(new Position("d7"), new Position("d5"));
		state.movePiece(new Position("h5"), new Position("f7"));

		
		assertEquals(1, state.checkForMate());
		// Only way to get out of check is take the Queen
		assertEquals(1, state.getPossibleMoveList(Player.Black).size());
	}

}
