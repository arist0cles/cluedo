import org.junit.*;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

public class CluedoTests {
	
	@Test
	public void testCorrectUsers() {
		String data = "4";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			Testhelper t = new Testhelper(1);
			Game game = new Game(t);
			assertTrue(game.getNumOfPlayers()==4);
			game = null;
		} finally {
			System.setIn(stdin);
		}
		
	}
	
	@Test
	public void testSolutionRemoved() {
		String data = "4";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			Testhelper t = new Testhelper(2);
			Game game = new Game(t);
			ArrayList<Card> solution = (ArrayList<Card>) game.getSolution();
			Card c  = solution.get(0);
			ArrayList<Player> players = (ArrayList)game.getPlayers();
			for(Player p : players){
				assertTrue(!p.getHand().contains(c));
			}
		} finally {
			System.setIn(stdin);
		}
	}
	
	@Test
	public void testSolutionRemoved2() {
		String data = "4";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			Testhelper t = new Testhelper(3);
			Game game = new Game(t);
			ArrayList<Card> solution = (ArrayList<Card>) game.getSolution();
			Card c  = solution.get(1);
			ArrayList<Player> players = (ArrayList)game.getPlayers();
			for(Player p : players){
				assertTrue(!p.getHand().contains(c));
			}
		} finally {
			System.setIn(stdin);
		}
	}

	/*@Test
	public void testTooManyUsers() {
		String data = "100";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			Testhelper t = new Testhelper(2);
			Game game = new Game(t);
			assertTrue(game.getNumOfPlayers()==0);
		} finally {
			System.setIn(stdin);
		}
	}
	
	@Test
	public void testNotEnoughUsers() {
		String data = "0";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			Testhelper t = new Testhelper(3);
			Game game = new Game(t);
			assertTrue(game.getNumOfPlayers()==4);
		} finally {
			System.setIn(stdin);
		}
	}*/
	
}