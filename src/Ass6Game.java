import component.GameEnvironment;
import component.LevelInformation;
import component.GameFlow;
import component.FinalFour;
import component.Green;
import component.WideEasy;
import component.DirectHit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ohad Marmor - 207481524.
 */
public class Ass6Game {

        // main:

        /**
         * the main function of the assignment.
         * @param args arguments for the main.
         */
      public static void main(String[] args) {
          GameEnvironment eO = new GameEnvironment();
          GameEnvironment eTw = new GameEnvironment();
          GameEnvironment eTh = new GameEnvironment();
          GameEnvironment eF = new GameEnvironment();
          List<LevelInformation> levels = new ArrayList<>();
          LevelInformation one = new DirectHit(eO);
          LevelInformation two = new WideEasy(eTw);
          LevelInformation three = new Green(eTh);
          LevelInformation four = new FinalFour(eF);
          levels.add(one);
          levels.add(two);
          levels.add(three);
          levels.add(four);
          GameFlow flow = new GameFlow();
          if (args.length == 0) {
              flow.runLevels(levels);
          } else {
              List<LevelInformation> levels1 = new ArrayList<>();
              for (String l : args) {
                  int num = 0;
                  if (l.length() == 1 && Character.isDigit(l.charAt(0))) {
                      num = Integer.parseInt(l);
                  }
                  if (num >= 1 && num <= 4) {
                      if (num == 1) {
                          levels1.add(one);
                      } else if (num == 2) {
                          levels1.add(two);
                      } else if (num == 3) {
                          levels1.add(three);
                      } else {
                          levels1.add(four);
                      }
                  }
              }
              if (levels1.isEmpty()) {
                  flow.runLevels(levels);
              } else {
                  flow.runLevels(levels1);
              }
          }
     }
}

