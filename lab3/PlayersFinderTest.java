package eg.edu.alexu.csd.datastructure.iceHockey.cs18011373;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.awt.Point;
import org.junit.jupiter.api.Test;

class PlayersFinderTest {

  PlayersFinder obj = new PlayersFinder();


  @Test
  void findPlayers() {
    final String[] arr = null;
    final String[] arr2 = {};
    //    Point[] players = new Point[6];
    //    players[0]= new Point(3,8);
    //    players[1]= new Point(4,16);
    //    players[2]= new Point(5,4);
    //    players[3]= new Point(16,3);
    //    players[4]= new Point (16,17);
    //    players[5]= new Point (17,9);
    final String[] arr1 = {
        "8D88888J8L8E888",
        "88NKMG8N8E8JI88",
        "888NS8EU88HN8EO",
        "LUQ888A8TH8OIH8",
        "888QJ88R8SG88TY",
        "88ZQV88B88OUZ8O",
        "FQ88WF8Q8GG88B8",
        "8S888HGSB8FT8S8",
        "8MX88D88888T8K8",
        "8S8A88MGVDG8XK8",
        "M88S8B8I8M88J8N",
        "8W88X88ZT8KA8I8",
        "88SQGB8I8J88W88",
        "U88H8NI8CZB88B8",
        "8PK8H8T8888TQR8"
    };
    Point[] players1 = new Point[19];
    players1[0] = new Point(1, 17);
    players1[1] = new Point(3, 3);
    players1[2] = new Point(3, 10);
    players1[3] = new Point(3, 25);
    players1[4] = new Point(5, 21);
    players1[5] = new Point(8, 17);
    players1[6] = new Point(9, 2);
    players1[7] = new Point(10, 9);
    players1[8] = new Point(12, 23);
    players1[9] = new Point(17, 16);
    players1[10] = new Point(18, 3);
    players1[11] = new Point(18, 11);
    players1[12] = new Point(18, 28);
    players1[13] = new Point(22, 20);
    players1[14] = new Point(23, 26);
    players1[15] = new Point(24, 15);
    players1[16] = new Point(27, 2);
    players1[17] = new Point(28, 26);
    players1[18] = new Point(29, 16);

    assertArrayEquals(null, obj.findPlayers(arr, 5, 12));
    assertArrayEquals(null, obj.findPlayers(arr2, 4, 16));
    assertArrayEquals(players1, obj.findPlayers(arr1, 8, 9));
  }
}
