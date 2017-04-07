/*  Copyright 2017 by AlaskaLinuxUser (https://thealaskalinuxuser.wordpress.com)
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package com.alaskalinuxuser.shipcaptainandcrew;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /*
     * Let's declare some things.
     */
    // TextViews
    TextView scoreCard;
    TextView rollsLeft;
    TextView highScore;
    TextView playerName;
    TextView winnerView;
    // Strings
    String myScore;
    // Buttons
    Button resetGame;
    Button rollButton;
    Button keepButton;
    // Integers
    int die;
    int rolls;
    int score;
    int firstDie;
    int secondDie;
    int thirdDie;
    int fourthDie;
    int fifthDie;
    int myNumber;
    int id;
    int scoreDieOne;
    int scoreDieTwo;
    int players;
    int playOneScore;
    int playTwoScore;
    int playThreeScore;
    int playFourScore;
    // Booleans
    boolean ship;
    boolean capt;
    boolean crew;
    boolean inPlay;
    boolean firstnotchecked;
    boolean secondnotchecked;
    boolean thirdnotchecked;
    boolean fourthnotchecked;
    boolean fifthnotchecked;
    boolean waschecked;
    boolean twoNoKeep;
    boolean threeNoKeep;
    boolean fourNoKeep;
    boolean fiveNoKeep;
    boolean oneNoKeep;
    boolean multiPlayers;
    // ImageViews
    ImageView battleship;
    ImageView capthat;
    ImageView crewhat;
    ImageView dieOne;
    ImageView dieTwo;
    ImageView dieThree;
    ImageView dieFour;
    ImageView dieFive;
    ImageView cargoView;
    LinearLayout layoutPlayers;
    LinearLayout layoutVideo;
    // And the context
    Context c;
    // Declare the media player.
    MediaPlayer mp;

    // So, what to do when we first open or "create" the app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lets play an opening video!

        // Find the video view by id so we can use it.
        VideoView myVideo = (VideoView) findViewById(R.id.videoView);

        // To set the path to the video. Sample.mp4 is in the "raw" folder.
        // You can use http://online.path.to.your.video also.
        myVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.destroyer_short);

        // To autoplay on opening.
        myVideo.start();

        // We've declared stuff, now lets define it.
        mp = MediaPlayer.create(this, R.raw.dice);
        scoreCard = (TextView)findViewById(R.id.scoreText);
        rollsLeft = (TextView)findViewById(R.id.remainingRolls);
        battleship = (ImageView)findViewById(R.id.battleship);
        capthat = (ImageView)findViewById(R.id.capthat);
        crewhat = (ImageView)findViewById(R.id.crewhat);
        dieOne = (ImageView)findViewById(R.id.imageView0);
        dieTwo = (ImageView)findViewById(R.id.imageView1);
        dieThree = (ImageView)findViewById(R.id.imageView2);
        dieFour = (ImageView)findViewById(R.id.imageView3);
        dieFive = (ImageView)findViewById(R.id.imageView4);
        cargoView = (ImageView)findViewById(R.id.cargoView);
        resetGame = (Button)findViewById(R.id.resetButton);
        c = getApplicationContext();
        layoutPlayers = (LinearLayout)findViewById(R.id.layoutPlayers);
        layoutVideo = (LinearLayout)findViewById(R.id.layoutVideo);
        rollButton = (Button)findViewById(R.id.rollButton);
        keepButton = (Button)findViewById(R.id.keepButton);
        highScore = (TextView)findViewById(R.id.highScoreText);
        playerName = (TextView)findViewById(R.id.playerName);
        winnerView = (TextView)findViewById(R.id.winnerView);

        // And, upon opening, let's reset the game, just in case.
        resetTheGame();

        // On a nougat phone, the roll button was covering the "about" button. We can't have that.
        rollButton.setVisibility(View.INVISIBLE);

    }

    // Here is the method to reset the game pieces.
    public void resetTheGame() {

        // Hide the reset button, because the game is already reset.
        resetGame.setVisibility(View.INVISIBLE);

        // Hide the keep it button, because you have not rolled yet.
        keepButton.setVisibility(View.INVISIBLE);

        // And keep he roll button invisible.
        rollButton.setVisibility(View.INVISIBLE);

        // set the text.
        scoreCard.setText("0");
        rollsLeft.setText("Rolls: 3");
        playerName.setText("Player " + players);
        highScore.setText("0");
        rollButton.setText("Roll!");
        winnerView.setText("No Winner!");

        // set the integers.
        die = 5;
        rolls = 3;
        score = 0;
        myNumber = 0;
        scoreDieOne = 0;
        scoreDieTwo = 0;
        players = 0;
        playOneScore = 0;
        playTwoScore = 0;
        playThreeScore = 0;
        playFourScore = 0;

        // set the booleans.
        ship = false;
        capt = false;
        crew = false;
        inPlay = true;
        firstnotchecked = true;
        secondnotchecked = true;
        thirdnotchecked = true;
        fourthnotchecked = true;
        fifthnotchecked = true;
        waschecked = false;
        oneNoKeep = true;
        twoNoKeep = true;
        threeNoKeep = true;
        fourNoKeep = true;
        fiveNoKeep = true;

        // set the images.
        battleship.setImageResource(R.drawable.battleship);
        capthat.setImageResource(R.drawable.caphat);
        crewhat.setImageResource(R.drawable.crewhat);
        dieOne.setImageResource(R.drawable.one);
        dieTwo.setImageResource(R.drawable.two);
        dieThree.setImageResource(R.drawable.three);
        dieFour.setImageResource(R.drawable.four);
        dieFive.setImageResource(R.drawable.five);

        // fade the images.
        battleship.setAlpha(.5f);
        capthat.setAlpha(.5f);
        crewhat.setAlpha(.5f);
        cargoView.setAlpha(.5f);

        // restore the dice.
        dieOne.setAlpha(1f);
        dieTwo.setAlpha(1f);
        dieThree.setAlpha(1f);
        dieFour.setAlpha(1f);
        dieFive.setAlpha(1f);

        // Roll the dice back.
        dieOne.animate().rotation(0f).setDuration(0);
        dieTwo.animate().rotation(0f).setDuration(0);
        dieThree.animate().rotation(0f).setDuration(0);
        dieFour.animate().rotation(0f).setDuration(0);
        dieFive.animate().rotation(0f).setDuration(0);

        // And hide the winner view.
        winnerView.setVisibility(View.INVISIBLE);
        winnerView.setAlpha(0f);

        // And the display question of number of players.
        layoutPlayers.setVisibility(LinearLayout.VISIBLE);
    }

    // To enter the game from the intro video.
    public void enterGame (View gameView) {

        // Go ahead and hide the video.
        layoutVideo.setVisibility(LinearLayout.GONE);

        // And set the roll button visable again.
        rollButton.setVisibility(View.INVISIBLE);

    }

    // How many players?
    public void numPlay (View playView) {

        // The first part of the method is to hide the displayed question of choices.
        layoutPlayers.setVisibility(LinearLayout.INVISIBLE);

        // Here we set the rules for the number of players.
        // Let's get the tag, which is a string.
        String tagId = (String) playView.getTag();
        // We will turn it into an integer.
        int tagNum = Integer.parseInt(tagId);
        // Now, my tags are multiples of 5, so we divide by 5 to get the player numbers.
        players = tagNum/5;

        // And set the player "name".
        playerName.setText("Player " + players);

        // Now to flag multiplayers.
        if (players >= 2) {

            multiPlayers = true;

        } else {

            multiPlayers = false;
        }

        // Now we can set the rollbutton to visable.
        rollButton.setVisibility(View.VISIBLE);

    }

    // We need to tie the reset button into the reset game call. I'll use an on-click method.
    public void startOver (View view) {

        // And call our resetGame method when we click on the reset button.
        resetTheGame();

    }

    // Of course, we need to tell people about our open source app, the github link for source, etc.
    public void aboutApp (View v)
    {
        // Call an intent to go to the second screen when you click the about button.
        // First you define it.
        Intent myintent = new Intent(MainActivity.this, AboutApp.class);
        // Now you call it.
        startActivity(myintent);
    }

    /*
     * A simple method to control the turn count, or remaining rolls. We could have added this
     * into the main turn method, but I thought it best to keep it seperate, in the event I want
     * to modify it, or use it in another app.
     */
    public void turnCount() {

        // We should decrease the rolls integer by 1.
        rolls--;

        // And we should update the counter.
        rollsLeft.setText("Rolls: " + Integer.toString(rolls));

    }

    // We need to keep score, and check for a winning condition.
    public void scoreCount() {

        // So, only check for a score if you have a ship captain and crew.
        if (ship && capt && crew) {

            // Every round I reset the score to zero and recheck with the remaining dice.
            scoreDieOne = 0;
            scoreDieTwo = 0;

            // These checks see if the dice is available to be a score dice, i.e., not a
            // ship captain or crew dice.
            if (firstnotchecked) {

                // To which, it sets it to one of the two posible score keepers.
                if (scoreDieOne == 0) {

                    scoreDieOne = firstDie;

                } else {

                    scoreDieTwo = firstDie;

                }

            }
            if (secondnotchecked) {

                if (scoreDieOne == 0) {

                    scoreDieOne = secondDie;

                } else {

                    scoreDieTwo = secondDie;

                }

            }
            if (thirdnotchecked) {

                if (scoreDieOne == 0) {

                    scoreDieOne = thirdDie;

                } else {

                    scoreDieTwo = thirdDie;

                }

            }
            if (fourthnotchecked) {

                if (scoreDieOne == 0) {

                    scoreDieOne = fourthDie;

                } else {

                    scoreDieTwo = fourthDie;

                }

            }
            if (fifthnotchecked) {

                if (scoreDieOne == 0) {

                    scoreDieOne = fifthDie;

                } else {

                    scoreDieTwo = fifthDie;

                }

            }

            // So, now we have a score.
            score = scoreDieOne + scoreDieTwo;
            // Which we convert to a string.
            myScore = String.valueOf(score);
            // Here we will set the scoreCard text.
            scoreCard.setText(myScore);
            // Since we have a score, we show the cargo icon brightly.
            cargoView.setAlpha(1f);

            // else, if there is not a ships captain and crew, the score is 0.
        } else {

            // Here we will set the scoreCard text.
            scoreCard.setText("0");

        }

        // A special tid bit, based on lore, a double six score is called "midnight" by ol' timey sailors.
        if (score == 12) {

            // We go ahead and end the current game, since they have the highest score possible, there
            // is no reason to roll again.
            rolls = 0;

            // And we give them a toast!
            Toast midnightToast = Toast.makeText(getApplicationContext(), "It's midnight Mate!", Toast.LENGTH_LONG);
            midnightToast.setGravity(Gravity.CENTER, 0, 0);
            midnightToast.show();
        }

        // And here we check the win or lose conditions, only when we have no rolls left....
        if (rolls == 0) {

            // But we must have a score to be a winner.
          if (score >= 1) {

             // Let's tell the user!
            Toast.makeText(getApplicationContext(), "Your Ship, Captain, and Crew have a Cargo of "
            + score + " points!", Toast.LENGTH_LONG).show();

             // Or if we have no score, but we had a ship and captain.
          } else if (ship && capt && crew == false) {

             // Let's tell the user!
             Toast.makeText(getApplicationContext(), "Your Ship and Captain have no Crew!",
                     Toast.LENGTH_LONG).show();

             // Or if we only have a ship.
          } else if (ship && capt == false) {

             // Let's tell the user!
             Toast.makeText(getApplicationContext(), "Your Ship has no Captain nor Crew!",
                     Toast.LENGTH_LONG).show();

             // Or if we are so unlucky as to have nothing.
          } else if (ship == false) {

             // Let's tell the user!
             Toast.makeText(getApplicationContext(), "You have no Ship, no Captain, and no Crew!",
                     Toast.LENGTH_LONG).show();
          }
            // What to do if there are more players.
            if (players >= 2) {

                // Set the roll button to be "next Player".
                rollButton.setText("Next Player");

            }

        } else {

            if (score >= 1) {

                // Since we hava a score, we should show the keep button.
                keepButton.setVisibility(View.VISIBLE);

            }
        }

    }

    // Ah, the method to make our random dice.
    public void randomDice() {

        // The random generator.
        Random diceRan = new Random();

        // A check for each dice if it is not checked ( a ship capt crew ) or kept ( held by the user).
        if (firstnotchecked && oneNoKeep) {

            // We assign a random number.
            firstDie = diceRan.nextInt(6) + 1;
            // We also give that number to the myNumber variable used in the dice check method.
            myNumber = firstDie;
            // We call the dice check method.
            diceCheck();
            // We check if the dice check returned checked through a boolean.
            if (waschecked){
                // if so, we set our specific dice boolean for the remainder of this game.
                firstnotchecked = false;
            }

            // And we set the image to corrospond with the dice roll, using a variable called id.
            dieOne.setImageResource(id);

        }

        // See dice one.
        if (secondnotchecked && twoNoKeep) {

            secondDie = diceRan.nextInt(6) + 1;
            myNumber = secondDie;
            diceCheck();
            if (waschecked){
                secondnotchecked = false;
            }

            dieTwo.setImageResource(id);

        }

        // See dice one.
        if (thirdnotchecked && threeNoKeep) {

            thirdDie = diceRan.nextInt(6) + 1;
            myNumber = thirdDie;
            diceCheck();
            if (waschecked){
                thirdnotchecked = false;
            }

            dieThree.setImageResource(id);

        }

        // See dice one.
        if (fourthnotchecked && fourNoKeep) {

            fourthDie = diceRan.nextInt(6) + 1;
            myNumber = fourthDie;
            diceCheck();
            if (waschecked){
                fourthnotchecked = false;
            }

            dieFour.setImageResource(id);

        }

        // See dice one.
        if (fifthnotchecked && fiveNoKeep) {

            fifthDie = diceRan.nextInt(6) + 1;
            myNumber = fifthDie;
            diceCheck();
            if (waschecked){
                fifthnotchecked = false;
            }

            dieFive.setImageResource(id);

        }

    }

    // Now our method to check the dice to see if it is part of the winning required set.
    public void diceCheck() {

        // So, first we check if they already have a ship capt and crew, like for the second/third rolls.
        if (ship && capt && crew) {

                // WOW, They have a ship captain and crew already!
                waschecked = false;

                // And we check each die and put the appropriate picture on it.
                if (myNumber == 6) {

                    // Set the string for the visual to 6.
                    id = c.getResources().getIdentifier("drawable/"+"six", null, c.getPackageName());

                } else if (myNumber == 5) {

                    // Set the string for the visual to 5.
                    id = c.getResources().getIdentifier("drawable/"+"five", null, c.getPackageName());

                } else if (myNumber == 4) {

                    // Set the string for the visual to 4.
                    id = c.getResources().getIdentifier("drawable/"+"four", null, c.getPackageName());

                } else if (myNumber == 3) {

                    // Set the string for the visual to 3.
                    id = c.getResources().getIdentifier("drawable/"+"three", null, c.getPackageName());

                } else if (myNumber == 2) {

                    // Set the string for the visual to 2.
                    id = c.getResources().getIdentifier("drawable/"+"two", null, c.getPackageName());

                } else {

                    // Set the string for the visual to 1.
                    id = c.getResources().getIdentifier("drawable/"+"one", null, c.getPackageName());

                }

            // Did they alread have a ship and captain, but no crew?
        } else if (ship && capt && crew == false) {

            // if our variable is a four, it is the crew dice now.
            if (myNumber == 4) {

                // set our boolean.
                crew = true;
                // set our drawable icon. E.g., check the crew hat.
                crewhat.setImageResource(R.drawable.crewcheck);
                // Set the crew hat alpha to full bright.
                crewhat.setAlpha(1f);
                // return that this dice was checked, or made special.
                waschecked = true;
                // Set the dice image to a checkmarked four.
                id = c.getResources().getIdentifier("drawable/"+"fourcheck", null, c.getPackageName());

            } else {

                // If not, well, return this dice is not special.
                waschecked = false;

                // And we check each die and put the appropriate picture on it.
                if (myNumber == 6) {

                    // Set the string for the visual to 6.
                    id = c.getResources().getIdentifier("drawable/"+"six", null, c.getPackageName());

                } else if (myNumber == 5) {

                    // Set the string for the visual to 5.
                    id = c.getResources().getIdentifier("drawable/"+"five", null, c.getPackageName());

                } else if (myNumber == 4) {

                    // Set the string for the visual to 4.
                    id = c.getResources().getIdentifier("drawable/"+"four", null, c.getPackageName());

                } else if (myNumber == 3) {

                    // Set the string for the visual to 3.
                    id = c.getResources().getIdentifier("drawable/"+"three", null, c.getPackageName());

                } else if (myNumber == 2) {

                    // Set the string for the visual to 2.
                    id = c.getResources().getIdentifier("drawable/"+"two", null, c.getPackageName());

                } else {

                    // Set the string for the visual to 1.
                    id = c.getResources().getIdentifier("drawable/"+"one", null, c.getPackageName());

                }

            }

            // What if there is a ship, but nothing else?
        } else if (ship && capt == false) {

            // Same as above, see crew text for explanation.
            if (myNumber == 5) {

                capt = true;
                capthat.setImageResource(R.drawable.capcheck);
                capthat.setAlpha(1f);
                waschecked = true;
                id = c.getResources().getIdentifier("drawable/"+"fivecheck", null, c.getPackageName());

            } else {

                waschecked = false;

                // And we check each die and put the appropriate picture on it.
                if (myNumber == 6) {

                    // Set the string for the visual to 6.
                    id = c.getResources().getIdentifier("drawable/"+"six", null, c.getPackageName());

                } else if (myNumber == 5) {

                    // Set the string for the visual to 5.
                    id = c.getResources().getIdentifier("drawable/"+"five", null, c.getPackageName());

                } else if (myNumber == 4) {

                    // Set the string for the visual to 4.
                    id = c.getResources().getIdentifier("drawable/"+"four", null, c.getPackageName());

                } else if (myNumber == 3) {

                    // Set the string for the visual to 3.
                    id = c.getResources().getIdentifier("drawable/"+"three", null, c.getPackageName());

                } else if (myNumber == 2) {

                    // Set the string for the visual to 2.
                    id = c.getResources().getIdentifier("drawable/"+"two", null, c.getPackageName());

                } else {

                    // Set the string for the visual to 1.
                    id = c.getResources().getIdentifier("drawable/"+"one", null, c.getPackageName());

                }

            }

            // But what if there is no ship captain or crew yet?
        } else {

            // Same as above, see crew text for explanation.
            if (myNumber == 6) {

                ship = true;
                battleship.setImageResource(R.drawable.batcheck);
                battleship.setAlpha(1f);
                waschecked = true;
                id = c.getResources().getIdentifier("drawable/"+"sixcheck", null, c.getPackageName());

            } else {

                waschecked = false;

                // And we check each die and put the appropriate picture on it.
                if (myNumber == 6) {

                    // Set the string for the visual to 6.
                    id = c.getResources().getIdentifier("drawable/"+"six", null, c.getPackageName());

                } else if (myNumber == 5) {

                    // Set the string for the visual to 5.
                    id = c.getResources().getIdentifier("drawable/"+"five", null, c.getPackageName());

                } else if (myNumber == 4) {

                    // Set the string for the visual to 4.
                    id = c.getResources().getIdentifier("drawable/"+"four", null, c.getPackageName());

                } else if (myNumber == 3) {

                    // Set the string for the visual to 3.
                    id = c.getResources().getIdentifier("drawable/"+"three", null, c.getPackageName());

                } else if (myNumber == 2) {

                    // Set the string for the visual to 2.
                    id = c.getResources().getIdentifier("drawable/"+"two", null, c.getPackageName());

                } else {

                    // Set the string for the visual to 1.
                    id = c.getResources().getIdentifier("drawable/"+"one", null, c.getPackageName());

                }

            }

        }

    }

    // And we want the dice to "roll".
    public void lookCool() {

        // We check each dice first to see if it is checked, we wont roll those.
        if (firstnotchecked) {

            // And we animate the dice with an added rotation from its current position.
            dieOne.animate().rotation(dieOne.getRotation() + 1080f).setDuration(2500);
        }

        if (secondnotchecked) {

            dieTwo.animate().rotation(dieTwo.getRotation() + 1080f).setDuration(2500);
        }

        if (thirdnotchecked) {

            dieThree.animate().rotation(dieThree.getRotation() + 1080f).setDuration(2500);
        }
        if (fourthnotchecked) {

            dieFour.animate().rotation(dieFour.getRotation() + 1080f).setDuration(2500);
        }
        if (fifthnotchecked) {

            dieFive.animate().rotation(dieFive.getRotation() + 1080f).setDuration(2500);
        }

    }

    // This method lets us click on the dice to keep them when we already have our
    // ships captain and crew. Like if you want to keep a six, but roll the one.

    public void keepThem (View keepView){

        // So, we need an if/then statement. We can only choose to keep dice under these conditions:
        // We have a ship, captain, and crew, AND we have at least one roll left.

        if (crew && rolls >= 1) {

            // We'll use a switch and case statement.
            switch (keepView.getId()) {
                case R.id.imageView0:
                    // set the boolean and dim the dice to show it is kept.
                    oneNoKeep = false;
                    dieOne.setAlpha(.5f);
                    break;
                case R.id.imageView1:
                    // set the boolean and dim the dice to show it is kept.
                    twoNoKeep = false;
                    dieTwo.setAlpha(.5f);
                    break;
                case R.id.imageView2:
                    // set the boolean and dim the dice to show it is kept.
                    threeNoKeep = false;
                    dieThree.setAlpha(.5f);
                    break;
                case R.id.imageView3:
                    // set the boolean and dim the dice to show it is kept.
                    dieFour.setAlpha(.5f);
                    fourNoKeep = false;
                    break;
                case R.id.imageView4:
                    // set the boolean and dim the dice to show it is kept.
                    fiveNoKeep = false;
                    dieFive.setAlpha(.5f);
                    break;

            };

        }
    }

    // This allows us to keep all of the dice.
    public void keepIt (View view) {

        // When they want to keep what they have. It should only be an option when you are winning.
        if (crew && rolls >= 1) {

            // Set the rolls to 0.
            rolls = 0;
            // and call the score count again.
            scoreCount();
        }

    }

    public void highScore() {

        // How to determine the scores of multiple players.
        // First, let's grab the scores.
        if (players == 4) {

            playOneScore = score;

        } else if (players == 3) {

            playTwoScore = score;

        } else if (players == 2) {

            playThreeScore = score;

        } else {

            playFourScore = score;

        }

        // Now that we have the scores, let's find the highest one.
        if (playFourScore > playThreeScore && playFourScore > playTwoScore && playFourScore > playOneScore) {

            // Since fourscore is bigger than threescore.
            highScore.setText(String.valueOf(playFourScore));
            // Confusing I know, but remember, the numbers are backwards.
            winnerView.setText("Player One wins with a score of " + playFourScore);

        } else if (playThreeScore > playFourScore && playThreeScore > playTwoScore && playThreeScore > playOneScore) {

            highScore.setText(String.valueOf(playThreeScore));
            // Confusing I know, but remember, the numbers are backwards.
            winnerView.setText("Player Two wins with a score of " + playThreeScore);

        } else if (playTwoScore > playOneScore && playTwoScore > playThreeScore && playTwoScore > playFourScore) {

            highScore.setText(String.valueOf(playTwoScore));
            // Confusing I know, but remember, the numbers are backwards.
            winnerView.setText("Player Three wins with a score of " + playTwoScore);

        } else if (playOneScore > playTwoScore && playOneScore > playThreeScore && playOneScore > playFourScore) {

            highScore.setText(String.valueOf(playOneScore));
            // Confusing I know, but remember, the numbers are backwards.
            winnerView.setText("Player Four wins with a score of " + playOneScore);

        } else {

            if (playOneScore == 0 && playTwoScore == 0 && playThreeScore == 0 && playFourScore ==0) {

                // Everyone's score is zero, so it is a tie, but that is confusion, so let's say 0.
                highScore.setText("0");
                winnerView.setText("This ship has sailed, because there is no Winner!");

            } else {

                // It must be a tie, since somebody has a score bigger than zero.
                highScore.setText("TIE!");
                winnerView.setText("It's a tie!");
            }

        }

    }

    // Finally, we want to actually play the game by calling all the methods we just made.
    public void rollDice (View view) {

        // We will now show the reset button, in case they want to use it.
        resetGame.setVisibility(View.VISIBLE);

        // As long as we still have rolls left, we can do this.
        if (rolls >= 1) {

            //Play the sound file.
            mp.start();

            // Let's call the turn counter method.
            turnCount();

            // Let's look cool rolling our dice!
            lookCool();

            // Let's call our random dice method.
            randomDice();

            // And let's update the score.
            scoreCount();

            // And the High score.
            highScore();

            // And the last roll for multiplayer.
            if (players == 1 && rolls == 0 && multiPlayers) {

                // Set the winner view visible.
                winnerView.setVisibility(View.VISIBLE);
                winnerView.animate().alpha(1f).setDuration(1500);

                // And hide the battleship and the top dice.
                battleship.animate().alpha(.0f).setDuration(1000);
                dieOne.animate().alpha(.0f).setDuration(1000);
                keepButton.setVisibility(View.INVISIBLE);

                // And the roll button.
                rollButton.setVisibility(View.INVISIBLE);

            } else {

                // Make sure the winner view is not visible.
                winnerView.setVisibility(View.INVISIBLE);

            }

            // But if we have no rolls left, we can't play any more.
        } else {

            highScore();

            if (players >= 2) {

                // We need to minus one player.
                players--;

                // We only want to reset SOME things, not all. So let's do that:
                // set the text.
                scoreCard.setText("0");
                rollsLeft.setText("Rolls: 3");
                rollButton.setText("Roll!");

                // Set Player name/number.
                    playerName.setText("Player " + players);


                // Hide the keep it button, because you have not rolled yet.
                keepButton.setVisibility(View.INVISIBLE);

                // set the integers.
                die = 5;
                rolls = 3;
                score = 0;
                myNumber = 0;
                scoreDieOne = 0;
                scoreDieTwo = 0;

                // set the booleans.
                ship = false;
                capt = false;
                crew = false;
                inPlay = true;
                firstnotchecked = true;
                secondnotchecked = true;
                thirdnotchecked = true;
                fourthnotchecked = true;
                fifthnotchecked = true;
                waschecked = false;
                oneNoKeep = true;
                twoNoKeep = true;
                threeNoKeep = true;
                fourNoKeep = true;
                fiveNoKeep = true;

                // set the images.
                battleship.setImageResource(R.drawable.battleship);
                capthat.setImageResource(R.drawable.caphat);
                crewhat.setImageResource(R.drawable.crewhat);
                dieOne.setImageResource(R.drawable.one);
                dieTwo.setImageResource(R.drawable.two);
                dieThree.setImageResource(R.drawable.three);
                dieFour.setImageResource(R.drawable.four);
                dieFive.setImageResource(R.drawable.five);

                // fade the images.
                battleship.setAlpha(.5f);
                capthat.setAlpha(.5f);
                crewhat.setAlpha(.5f);
                cargoView.setAlpha(.5f);

                // restore the dice.
                dieOne.setAlpha(1f);
                dieTwo.setAlpha(1f);
                dieThree.setAlpha(1f);
                dieFour.setAlpha(1f);
                dieFive.setAlpha(1f);

                // Roll the dice back.
                dieOne.animate().rotation(0f).setDuration(0);
                dieTwo.animate().rotation(0f).setDuration(0);
                dieThree.animate().rotation(0f).setDuration(0);
                dieFour.animate().rotation(0f).setDuration(0);
                dieFive.animate().rotation(0f).setDuration(0);

                // For the last player, or non multiplayer.
            } else {

                if (multiPlayers){

                    /* We could do something here, but for multiplayer mode, it is already
                     * transitioning to the winner screen.
                     * So there is no cause for action. This button should be invisable.
                     */

                } else {

                    // So let's tell the user(s) they are out of rolls. Indicating that they should reset the game.
                    Toast.makeText(getApplicationContext(), "The game is over! Reset to play again.",
                            Toast.LENGTH_SHORT).show();

                    /*
                     * You could set "reset game" here in the "else" block, but then click happy users may not
                     * realize it and lose their score. So I left it this way, forcing them to click "reset"
                     * when they want to play again.
                     */

                }

            }

        }

    }

}
