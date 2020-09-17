/**
 * 
 */
package test;

/**
 * test the game logic
 * @author Cuong Nguyen 783901
 *
 */
import player.Movement;

public class TestGamePlay

{

	/**
	 * @param args
	 */



	private Movement move;

	public TestGamePlay()
	{
		move = new Movement();
//		for (int i = 0; i < 5; i++) {
//	boolean a =	move.updateMove(1, i);
//		if (a == true)
//		{
//				System.out.println("win");
//			}
//		}
		
//		for (int i = 5; i > 0; i--) {
//	boolean a =	move.updateMove(1, i);
//		if (a == true)
//		{
//				System.out.println("win");
//			}
//		}
		
//		for (int i = 5; i > 0; i--) {
//	boolean a =	move.updateMove(i, 1);
//		if (a == true)
//		{
//				System.out.println("win");
//			}
//		}
		for (int i = 5; i < 10; i++) {
	boolean a =	move.updateMove(i, 1);
		if (a == true)
		{
				System.out.println("win");
			}
		}
	}

}
