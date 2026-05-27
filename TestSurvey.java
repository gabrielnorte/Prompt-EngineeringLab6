import java.util.*;

public class TestSurvey {
    public static void main(String[] args) {
        try {
            SurveyManager m = new SurveyManager();
            List<Question> qs = new ArrayList<>();
            qs.add(new Question("What is your favorite color?"));
            Survey s = new Survey("Colors", qs);
            m.addSurvey(s);
            if (m.getSurvey(1) == null) throw new RuntimeException("Survey not added");
            s.addResponse(Arrays.asList("Blue"));
            if (s.getResponses().size() != 1) throw new RuntimeException("Response not recorded");
            m.saveToDisk();
            SurveyManager m2 = new SurveyManager();
            m2.loadFromDisk();
            if (m2.getSurvey(1) == null) throw new RuntimeException("Loaded survey missing");
            System.out.println("TESTS PASSED");
        } catch (Throwable t) {
            System.err.println("TESTS FAILED: " + t.getMessage());
            t.printStackTrace();
            System.exit(2);
        }
    }
}
