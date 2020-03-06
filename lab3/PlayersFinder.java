package eg.edu.alexu.csd.datastructure.iceHockey.cs18011373;

import java.awt.Point;
import java.util.Arrays;


public class PlayersFinder implements IPlayersFinder {

  private static int n; // n is number of players found
  public int minX;
  public int minY;
  public int maxX;
  public int maxY;

  private static void main(String[] args) {
    String[] arr = {
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
    Point[] players = new Point[n];
    for (int i = 0; i < n; i++) {
      players[i] = new Point();
    }
    PlayersFinder obj = new PlayersFinder();
    players = obj.findPlayers(arr, 8, 9);


    System.out.println(players[0].toString());
  }

  private int thresholdCheck(char[][] arrCharT, int x, int y, int team) {
    int area = 4;
    arrCharT[y][x] = ' ';
    if (x > maxX) {
      maxX = x;
    }
    if (y > maxY) {
      maxY = y;
    }
    if (x < minX) {
      minX = x;
    }
    if (y < minY) {
      minY = y;
    }
    if (x + 1 < arrCharT[0].length && arrCharT[y][x + 1] == team + 48) {
      area = thresholdCheck(arrCharT, x + 1, y, team) + area;
    }
    if (y + 1 < arrCharT.length && arrCharT[y + 1][x] == team + 48) {
      area = thresholdCheck(arrCharT, x, y + 1, team) + area;
    }
    if (x - 1 >= 0 && arrCharT[y][x - 1] == team + 48) {
      area = thresholdCheck(arrCharT, x - 1, y, team) + area;
    }
    if (y - 1 >= 0 && arrCharT[y - 1][x] == team + 48) {
      area = thresholdCheck(arrCharT, x, y - 1, team) + area;
    }
    return area;
  }

  @Override
  public Point[] findPlayers(String[] photo, int team, int threshold) {
    if (photo == null || photo.length == 0) {
      return null;
    }
    char[][] arrChar = new char[photo.length][photo[0].length()];
    Point[] centers = new Point[30];
    for (int i = 0; i < photo.length; i++) {
      arrChar[i] = photo[i].toCharArray();
    }
    for (int j = 0; j < arrChar.length; j++) {
      for (int i = 0; i < arrChar[0].length; i++) {
        if (arrChar[j][i] == team + 48) {
          minX = maxX = i;
          minY = maxY = j;
          if (thresholdCheck(arrChar, i, j, team) >= threshold) {
            centers[n] = new Point();
            centers[n].setLocation(maxX + minX + 1, maxY + minY + 1);
            n++;
          }
        }
      }
    }
    Arrays.sort(centers, (a, b) -> {
      if (a == null && b == null) {
        return 0;
      }
      if (a == null) {
        return 1;
      }
      if (b == null) {
        return -1;
      }
      int xcomp = Integer.compare(a.x, b.x);
      if (xcomp == 0) {
        return Integer.compare(a.y, b.y);
      } else {
        return xcomp;
      }
    });
    return Arrays.copyOf(centers,n);
  }
}
