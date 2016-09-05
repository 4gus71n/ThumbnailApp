package com.si.thumbnailapp;

import android.support.annotation.NonNull;

import com.si.thumbnailapp.model.Person;
import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Setting;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.ui.challenge.adapter.PlayerViewHolder;
import com.si.thumbnailapp.ui.challenge.view.ChallengeFragment;
import com.si.thumbnailapp.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23, application = TestThumbnailApp.class)
public class ChallengeFragmentTest {

    public static final String TEAM_NAME = "TeamName";
    private static final String SAMPLE_MESSAGE = "SampleMessage";
    private static final CharSequence APP_TITLE = "ThumbnailApp";

    public ChallengeFragment fragment;

    @Before
    public void setUp() throws Exception
    {
        fragment = ChallengeFragment.newInstance();
        MainActivity activity = Robolectric.buildActivity(MainActivity.class )
                .create()
                .start()
                .resume()
                .get();

        activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                fragment).commit();
    }

    /**
     * Test that the title in the activity's Toolbar is shown properly.
     * @throws Exception
     */
    @Test
    public void testTitle() throws Exception {
        fragment.setTeamTitle(getRandomTeam(1));
        assertTrue("The title doesn't match", TEAM_NAME.equals(fragment.getActivity().getTitle()));
    }

    /**
     * Test that the messages are properly shown in the snackbar
     * @throws Exception
     */
    @Test
    public void testMessage() throws Exception {
        fragment.showMessage(SAMPLE_MESSAGE);
        assertTrue("The message doesn't match", fragment.getLastSnackbar().isShownOrQueued());
    }

    /**
     * Tests that the list is kept empty if the player list is null
     * @throws Exception
     */
    @Test
    public void testNullPlayerList() throws Exception {
        fragment.loadPlayersList(getRandomTeam(1), null);
        assertTrue("The Adapter should be empty", fragment.getAdapter().getItemCount() == 0);
    }

    /**
     * Tests that the Toolbar title is kept if the team name is null
     * @throws Exception
     */
    @Test
    public void testNullTeam() throws Exception {
        fragment.loadPlayersList(null, null);
        assertTrue("The Adapter should be empty", fragment.getAdapter().getItemCount() == 0);
        assertTrue("The Activity title should be ThumbnailApp", APP_TITLE.equals(fragment.getActivity().getTitle()));
    }

    /**
     * Test that the player list is properly loaded
     * @throws Exception
     */
    @Test
    public void testPlayerList() throws Exception {
        Team randomTeamWithZeroPlayers = getRandomTeam(0);
        Team randomTeamWithOnePlayer = getRandomTeam(1);
        Team randomTeamWithOneHundredPlayers = getRandomTeam(100);
        fragment.loadPlayersList(randomTeamWithZeroPlayers, randomTeamWithZeroPlayers.getPlayers());
        assertTrue("The players amount doesn't match", fragment.getAdapter().getItemCount() == 0);
        fragment.loadPlayersList(randomTeamWithOnePlayer, randomTeamWithOnePlayer.getPlayers());
        assertTrue("The players amount doesn't match", fragment.getAdapter().getItemCount() == 1);
        fragment.loadPlayersList(randomTeamWithOneHundredPlayers, randomTeamWithOneHundredPlayers.getPlayers());
        assertTrue("The players amount doesn't match", fragment.getAdapter().getItemCount() == 100);
    }

    /**
     * Tests that the player ViewHolder fields are properly loaded
     * @throws Exception
     */
    @Test
    public void testPlayerViewHolderList() throws Exception {
        Team randomTeamWithOnePlayer = getRandomTeam(1);
        fragment.loadPlayersList(randomTeamWithOnePlayer, randomTeamWithOnePlayer.getPlayers());
        PlayerViewHolder viewHolder = fragment.getAdapter().onCreateViewHolder(fragment.gerRecyclerView(), 0);
        fragment.getAdapter().bindViewHolder(viewHolder, 0);
        Player player = randomTeamWithOnePlayer.getPlayers().get(0);
        assertTrue("Position doesn't match", viewHolder.getPositionTextView().getText().toString().equals(player.getJerseyNumber()));
        assertTrue("Name doesn't match", viewHolder.getNameTextView().getText().toString().equals(player.getPerson().getFirstName()));
        assertTrue("Last name doesn't match", viewHolder.getLastNameTextView().getText().toString().equals(player.getPerson().getLastName()));
    }

    //region Mockito methods
    @NonNull
    private Team getRandomTeam(int playersAmount) {
        Team defaultTeam = Mockito.mock(Team.class);
        Mockito.when(defaultTeam.getTeamName()).thenReturn(TEAM_NAME);
        ArrayList<Player> defaultPlayerList = new ArrayList<>();
        for (int i = 0; i < playersAmount; i++) {
            Player randomPlayer = getRandomPlayer();
            defaultPlayerList.add(randomPlayer);
        }
        Mockito.when(defaultTeam.getPlayers()).thenReturn(defaultPlayerList);
        Setting randomSettings = getRandomSettings();
        Mockito.when(defaultTeam.getSetting()).thenReturn(randomSettings);
        return defaultTeam;
    }

    @NonNull
    private Setting getRandomSettings() {
        Setting settings = Mockito.mock(Setting.class);
        Mockito.when(settings.getHighlightColor()).thenReturn("#ff323232");
        return settings;
    }

    @NonNull
    private Player getRandomPlayer() {
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getJerseyNumber()).thenReturn("11");
        Mockito.when(player.getId()).thenReturn(1);
        Person randomPerson = getRandomPerson();
        Mockito.when(player.getPerson()).thenReturn(randomPerson);
        return player;
    }

    @NonNull
    private Person getRandomPerson() {
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getFirstName()).thenReturn("Peter");
        Mockito.when(person.getLastName()).thenReturn("Parker");
        Mockito.when(person.getImageUrl()).thenReturn("www.somepic.com");
        return person;
    }
    //endregion
}