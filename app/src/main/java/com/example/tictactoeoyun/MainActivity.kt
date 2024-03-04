package com.example.tictactoeoyun

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var player = true // true: X, false: O
    private var board = Array(3) { IntArray(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resetBoard()
    }

    fun buttonClick(view: View) {
        if (view !is Button) return

        val row = view.tag.toString()[0].toInt() - '0'.toInt()
        val col = view.tag.toString()[1].toInt() - '0'.toInt()

        if (board[row][col] != 0) return

        board[row][col] = if (player) 1 else 2
        view.text = if (player) "X" else "O"
        player = !player

        if (checkForWin()) {
            val winner = if (player) "O" else "X"
            Toast.makeText(this, "Player $winner wins!", Toast.LENGTH_SHORT).show()
            resetBoard()
        }
    }

    private fun checkForWin(): Boolean {
        for (i in 0 until 3) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return true
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true
        }
        return board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]
    }

    fun resetGame(view: View) {
        resetBoard()
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val buttonId = resources.getIdentifier("button$i$j", "id", packageName)
                val button = findViewById<Button>(buttonId)
                button.text = ""
            }
        }
    }

    private fun resetBoard() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                board[i][j] = 0
            }
        }
        player = true
    }
}
