import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Main {

    public static void main(String[] args) {
        // Sample JSON data
        String sampleJSON = """
                {
                    "BookShelf": [
                        {
                            "title": "Book 1",
                            "publishedYear": 1999,
                            "numberOfPages": 923,
                            "authors": ["Author A"]
                        },
                        {
                            "title": "Book 2",
                            "publishedYear": 2005,
                            "numberOfPages": 250,
                            "authors": ["Author B"]
                        },
                        {
                            "title": "Book 3",
                            "publishedYear": 2010,
                            "numberOfPages": 400,
                            "authors": ["Author C", "Author D"]
                        }
                    ]
                }
                """;

        // Step 1: Parse and print the original JSON data
        System.out.println("==== Original Book Shelf ====");
        parseJSON(sampleJSON);

        // Step 2: Add a new book and print the updated JSON
        System.out.println("\n==== After Adding New Book ====");
        String updatedJSON = addBookToJSON(sampleJSON, "Book 4", 2022, 350, new String[]{"Author E", "Author F"});
        parseJSON(updatedJSON);
    }

    // Method to parse JSON and print the book details
    public static void parseJSON(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray bookShelf = jsonObject.getAsJsonArray("BookShelf");

        // Iterate through each book and print its details
        for (int i = 0; i < bookShelf.size(); i++) {
            JsonObject book = bookShelf.get(i).getAsJsonObject();
            String title = book.get("title").getAsString();
            int publishedYear = book.get("publishedYear").getAsInt();
            int numberOfPages = book.get("numberOfPages").getAsInt();
            JsonArray authors = book.getAsJsonArray("authors");

            System.out.println("Book Title: " + title);
            System.out.println("Published Year: " + publishedYear);
            System.out.println("Number of Pages: " + numberOfPages);
            System.out.print("Authors: ");
            for (int j = 0; j < authors.size(); j++) {
                System.out.print(authors.get(j).getAsString() + (j < authors.size() - 1 ? ", " : ""));
            }
            System.out.println("\n--------------------------------");
        }
    }

    // Method to add a new book to the JSON string
    public static String addBookToJSON(String json, String title, int publishedYear, int numberOfPages, String[] authors) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray bookShelf = jsonObject.getAsJsonArray("BookShelf");

        // Create a new book object
        JsonObject newBook = new JsonObject();
        newBook.addProperty("title", title);
        newBook.addProperty("publishedYear", publishedYear);
        newBook.addProperty("numberOfPages", numberOfPages);

        // Create authors array
        JsonArray authorsArray = new JsonArray();
        for (String author : authors) {
            authorsArray.add(author);
        }
        newBook.add("authors", authorsArray);

        // Add the new book to the JSON array
        bookShelf.add(newBook);

        // Return the updated JSON string
        return gson.toJson(jsonObject);
    }
}