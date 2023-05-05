package csc325;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import com.google.firebase.cloud.FirestoreClient;
/**
 * JavaFX App
 */
public class App extends Application {
    
    public class FirestoreContext {
        public Firestore firebase() {
            try {
                FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("csc325weatherlens-firebase-adminsdk-d1394-0025d4fab8.json")))
                    .build();
                FirebaseApp.initializeApp(options);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return FirestoreClient.getFirestore();
        }
    }

    private static Scene scene;
    public static Stage stage = null;                                           // placeholder stage to hold the original version of the login page.

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("WeatherLens");
        stage.setResizable(false);
        this.stage = stage;
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Location location = new Location("Farmingdale, NY");
        System.out.println(location.toString());
        launch();
       
    }
}
