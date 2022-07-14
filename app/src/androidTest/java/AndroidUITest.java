import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.bicepgamers.Application.Services;
import com.example.bicepgamers.GUI.HomePage;
import com.example.bicepgamers.R;
import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.database.SessionPersistence;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.Util.MyViewAction;
import com.example.bicepgamers.objects.Session;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AndroidUITest {
    @Rule
    public ActivityTestRule<HomePage> homePageActivityTestRule = new ActivityTestRule<>(HomePage.class);

    @Test
    public void showAllGames(){

        onView(withId(R.id.games)).perform(click());
        onView(withId(R.id.addGameButton)).perform(click());

        //add new game
        onView(withId(R.id.gameNameTxt)).perform(typeText("diabloEspresso"), closeSoftKeyboard());
        //select moba
        onView(withId(R.id.genreSpinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        //select pc
        onView(withId(R.id.deviceSpinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.onlineSwitch)).perform(click());
        //create game
        onView(withId(R.id.createGameButton)).perform(click());

       //get games list
        GamePersistence gamePersistence = Services.getGamePersistence();
        List<Game> games = gamePersistence.getGames();
        // Scroll to end of page with position
        onView(withId(R.id.recyclerViewGames)).perform(RecyclerViewActions.scrollToPosition(games.size()-1));

        //check if diable is added
        onView(withText("DIABLOESPRESSO")).check(matches(isDisplayed()));

        //click on diable espresso
        onView(withId(R.id.recyclerViewGames)).perform(RecyclerViewActions.actionOnItemAtPosition(games.size()-1,click()));

        //start a session
        onView(withId(R.id.sessionButton)).perform(click());

        //have session for 5 secs
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //start break
        onView(withId(R.id.breakButton)).perform(click());

        //watch the first video
        onView(withId(R.id.workoutvideos)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id. videobtn)));

        //watch video for 5 secs
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //close video
        pressBack();

        //end break
        onView(withId(R.id.endbreakbutton)).perform(click());

        //be in session for 5 more secs
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //end session
        onView(withId(R.id.sessionButton)).perform(click());
        onView(withText("End")).perform(click());

        //check session history
        onView(withId(R.id.sessions)).perform(click());

        //get all session
        SessionPersistence sessionPersistence= Services.getSessionPersistence();
        List<Session> sessions =sessionPersistence.getSessions();
        onView(withId(R.id.recyclerViewSessions)).perform(RecyclerViewActions.scrollToPosition(sessions.size()-1));

        //check the correct break times are displayed
        onView(withText("DIABLOESPRESSO")).check(matches(isDisplayed()));
        onView(withText("PC")).check(matches(isDisplayed()));
        onView(withText("MOBA")).check(matches(isDisplayed()));
        onView(withText("ONLINE")).check(matches(isDisplayed()));

   }

   @After
    public void tearDown(){
        //remove diablo espresso
       GamePersistence gamePersistence = Services.getGamePersistence();
       List<Game> games = gamePersistence.getGames();
       for(Game game: games){
           if(game.getGameName().equals("DIABLOESPRESSO")){
               gamePersistence.deleteGame(game);
           }
       }

       //remove the session
       SessionPersistence sessionPersistence= Services.getSessionPersistence();
       List<Session> sessions =sessionPersistence.getSessions();
       for (Session session: sessions){
           if(session.getGameName().equals("DIABLOESPRESSO")){
               sessionPersistence.deleteSession(session);
           }
       }
   }
}
