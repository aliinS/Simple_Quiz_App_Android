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
        mutableListOf("CSS", "Cascading Style Sheet", "Cultural Shit Sheet", "Computer Style Sorter", "Casual Style System"),
        mutableListOf("HTML", "HyperText Markup Language", "Hi To My Learners", "Hello Text Message Lineup", "High Text Making Language"),
        mutableListOf("HTTPS", "Hypertext Transfer Protocol Secure", "something", "something", "something"),
        mutableListOf("CAD", "Computer Aided Design", "Computer Activated Device", "Canadian Autocomplete Directory", "Casual Activation of Data"),
        mutableListOf("RAM", "Random Access Memory", "Rational Activity Murial", "Rural African Mandarin", "Random Activity Memory"),
        mutableListOf("WWW", "World Wide Web", "What Would (Jesus) Walk on?", "Weird World Web", "Why Where When"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
        mutableListOf("CSS", "Cascading Style Sheet", "something", "something", "something"),
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