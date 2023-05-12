package com.example.simplequizapp
//right-click on java -> new -> new activity -> empty views activity.
//it will automatically create a .xml file as well (connecting is shown in MainActivity.kt)
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.simplequizapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {



    private lateinit var binding: ActivitySecondBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1

    private val finalQuizCount = 5

    private val quizData = mutableListOf(
        //abbreviation, answer, choice1, choice2, choice3
        mutableListOf("What does CSS mean?", "Cascading Style Sheet", "Cultural Shit Sheet", "Computer Style Sorter", "Casual Style System"),
        mutableListOf("What does HTML mean?", "HyperText Markup Language", "Hi To My Learners", "Hello Text Message Lineup", "High Text Making Language"),
        mutableListOf("What does HTTPS mean?", "Hypertext Transfer Protocol Secure", " Holy TriniTy Pizza Service", "High Tech Parachuting School", "Hybrid Tae-Tae Processing System"),
        mutableListOf("What does CAD mean?", "Computer Aided Design", "Computer Activated Device", "Canadian Autocomplete Directory", "Casual Activation of Data"),
        mutableListOf("What does RAM mean?", "Random Access Memory", "Rational Activity Murial", "Rural African Mandarin", "Random Activity Memory"),
        mutableListOf("What does WWW mean?", "World Wide Web", "What Would (Jesus) Walk on?", "Weird World Web", "Why Where When"),
        mutableListOf("What does AI mean?", "Artificial Intelligence", "Artificial Internet", "Accounted Info", "Accounted Idea"),
        mutableListOf("What does BPI mean?", "Bytes Per Inch", "Bring Personal Info", "Beer,Pub,In[0,2,1]", "Button Pusher Image"),
        mutableListOf("What does CPU mean?", "Central Processing Unit", "Correction Price in Ubuntu", "Create,Pursue,Uninstall", "Computer Protocol Uniform"),
        mutableListOf("What does DDL mean?", "Data Definition Language", "Driver Driving Length", "Drivers Data Lineup", "Dos Device Locator"),
        mutableListOf("What does FTP mean?", "File Transfer Protocol", "Forced Text Processing", "Figurative Text Procedure", "Formed To Perfection"),
        mutableListOf("What does HSS mean?", "Hierarchical Storage System", "HyperStorage Security", "High Security Storage", "Helping System Software"),
        mutableListOf("What does IBM mean?", "International Business Machine", "Intelligent Button Motion", "Image Bold Measurement", "Itty Bitty Machines"),
        mutableListOf("What does IP mean?", "Internet Protocol", "lorem IPsum", "Invisible Power", "Information Provider"),
        mutableListOf("What does SQL mean?", "Structured Query Language", "System Query Logic", "Sequential Query Language", "Sassy Queen's Lingo"),
        mutableListOf("What does URL mean?", "Uniform Resource Locator", "Useless Rubbish List", "Unique Resource Locator", "Unidentified Roaming Leopard"),
        mutableListOf("What does WAN mean?", "Wide Area Network", "Wireless Area Network", "Web Access News", "Wildly Absurd Nonsense"),
        mutableListOf("What does WAP mean?", "Wireless Application Protocol", "Web Access Point", "Wonky Apple Pie", "Weirdly Addictive Popcorn"),
        mutableListOf("What does WLAN mean?", "Wireless Local Area Network", "Weird Leprechaun Adventures Network", "Wireless Land Area Network", "World List of Area Networks"),
        mutableListOf("What does XML mean?", "eXtensible Markup Language", "Extra Markup List", "X-Ray Machine Learning", "Xena Markup Language"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //shuffle quiz
        quizData.shuffle()

        showNextQuiz()
    }

    private fun showNextQuiz() {

        //update countLabel (question counting)
        binding.countLabel.text = getString(R.string.count_label, quizCount)


        //pick one question with answers
        val quiz = quizData[0]

        //set question
        binding.question.text = quiz[0]
        //set right answer
        rightAnswer = quiz[1]

        //remove abbreviation/questions from quizData list (do I have to do that!?)
        quiz.removeAt(0)

        //shuffle answer and choices
        quiz.shuffle()

        //set choices
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        //remove asked question from quizData
        quizData.removeAt(0)
    }

    fun checkAnswer(view: View) {
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        //gets user input from MainActivity
        val usersName = intent.getStringExtra("NAME_INPUT")
        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "You know your abbreviations! Good job, $usersName!"
            rightAnswerCount++
        } else {
            alertTitle = "Oops.. wrong answer."
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer $rightAnswer")
            .setPositiveButton("OK") {dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    private fun checkQuizCount() {
        if (quizCount == finalQuizCount) {
            //show result

            val intent = Intent(this@SecondActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)



        } else {
            quizCount ++
            showNextQuiz()
        }
    }
}