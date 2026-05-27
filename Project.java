import java.io.*;
import java.util.*;

public class Project implements Serializable {
	public static void main(String[] args) {
		SurveyApp app = new SurveyApp();
		app.run();
	}
}

class SurveyApp {
	private final Scanner in = new Scanner(System.in);
	private final SurveyManager manager = new SurveyManager();

	void run() {
		manager.loadFromDisk();
		while (true) {
			System.out.println("\n=== Surveys App ===");
			System.out.println("1) Create survey");
			System.out.println("2) List surveys");
			System.out.println("3) Answer survey");
			System.out.println("4) Show survey results");
			System.out.println("5) Save surveys");
			System.out.println("0) Exit");
			System.out.print("Choose: ");
			String choice = in.nextLine().trim();
			switch (choice) {
				case "1": createSurvey(); break;
				case "2": manager.listSurveys(); break;
				case "3": takeSurvey(); break;
				case "4": showResults(); break;
				case "5": manager.saveToDisk(); break;
				case "0":
					manager.saveToDisk();
					System.out.println("Goodbye.");
					return;
				default: System.out.println("Invalid option");
			}
		}
	}

	private void createSurvey() {
		System.out.print("Survey title: ");
		String title = in.nextLine().trim();
		List<Question> questions = new ArrayList<>();
		System.out.println("Enter questions (blank line to finish):");
		int qnum = 1;
		while (true) {
			System.out.print("Q" + qnum + ": ");
			String q = in.nextLine();
			if (q == null || q.trim().isEmpty()) break;
			questions.add(new Question(q.trim()));
			qnum++;
		}
		if (questions.isEmpty()) {
			System.out.println("No questions added — survey cancelled.");
			return;
		}
		manager.addSurvey(new Survey(title, questions));
		System.out.println("Survey created.");
	}

	private void takeSurvey() {
		Integer id = manager.chooseSurvey(in);
		if (id == null) return;
		Survey s = manager.getSurvey(id);
		if (s == null) return;
		System.out.println("Answer the questions below (just press Enter to submit an empty answer).");
		List<String> resp = new ArrayList<>();
		for (Question q : s.getQuestions()) {
			System.out.println(q.getText());
			System.out.print("Answer: ");
			String a = in.nextLine();
			resp.add(a == null ? "" : a);
		}
		s.addResponse(resp);
		System.out.println("Thanks — response recorded.");
	}

	private void showResults() {
		Integer id = manager.chooseSurvey(in);
		if (id == null) return;
		Survey s = manager.getSurvey(id);
		if (s == null) return;
		System.out.println("\nResults for: " + s.getTitle());
		List<List<String>> responses = s.getResponses();
		if (responses.isEmpty()) {
			System.out.println("No responses yet.");
			return;
		}
		for (int qi = 0; qi < s.getQuestions().size(); qi++) {
			Question q = s.getQuestions().get(qi);
			System.out.println("\nQ: " + q.getText());
			int idx = 1;
			for (List<String> r : responses) {
				String answer = qi < r.size() ? r.get(qi) : "";
				System.out.println("  " + idx + ") " + answer);
				idx++;
			}
		}
	}
}

class SurveyManager implements Serializable {
	private final Map<Integer, Survey> surveys = new TreeMap<>();
	private int nextId = 1;
	private static final String DATA_FILE = "surveys.dat";

	void addSurvey(Survey s) {
		s.setId(nextId);
		surveys.put(nextId, s);
		nextId++;
	}

	Survey getSurvey(int id) { return surveys.get(id); }

	void listSurveys() {
		if (surveys.isEmpty()) {
			System.out.println("No surveys available.");
			return;
		}
		System.out.println("Surveys:");
		for (Map.Entry<Integer, Survey> e : surveys.entrySet()) {
			System.out.println(e.getKey() + ") " + e.getValue().getTitle() + " (" + e.getValue().getQuestions().size() + " q)");
		}
	}

	Integer chooseSurvey(Scanner in) {
		if (surveys.isEmpty()) { System.out.println("No surveys."); return null; }
		listSurveys();
		System.out.print("Enter survey id: ");
		String line = in.nextLine().trim();
		try { return Integer.parseInt(line); } catch (Exception e) { System.out.println("Invalid id"); return null; }
	}

	void saveToDisk() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
			oos.writeObject(surveys);
			oos.writeInt(nextId);
			System.out.println("Saved " + surveys.size() + " surveys to " + DATA_FILE);
		} catch (IOException e) {
			System.out.println("Save failed: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	void loadFromDisk() {
		File f = new File(DATA_FILE);
		if (!f.exists()) return;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			Object obj = ois.readObject();
			if (obj instanceof Map) {
				Map<Integer, Survey> loaded = (Map<Integer, Survey>) obj;
				surveys.clear();
				surveys.putAll(loaded);
				nextId = ois.readInt();
				System.out.println("Loaded " + surveys.size() + " surveys from " + DATA_FILE);
			}
		} catch (Exception e) {
			System.out.println("Load failed: " + e.getMessage());
		}
	}
}

class Survey implements Serializable {
	private int id;
	private final String title;
	private final List<Question> questions;
	private final List<List<String>> responses = new ArrayList<>();

	Survey(String title, List<Question> questions) {
		this.title = title;
		this.questions = questions;
	}

	String getTitle() { return title; }
	List<Question> getQuestions() { return questions; }
	List<List<String>> getResponses() { return responses; }
	void addResponse(List<String> r) { responses.add(r); }
	void setId(int id) { this.id = id; }
	int getId() { return id; }
}

class Question implements Serializable {
	private final String text;
	Question(String text) { this.text = text; }
	String getText() { return text; }
}
