package demo.amjadkhan.dtosample.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import demo.amjadkhan.dtosample.domain.model.Quote
import demo.amjadkhan.dtosample.ui.theme.DtoSampleTheme

class MainActivity : ComponentActivity() {

    // Creating the view model object for the quote view model
    // here we have used by viewModels() delegate to create the view model object
    private val quoteViewModel: QuoteViewModel by  viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DtoSampleTheme {
                // Now if we are using the compose then we need to use this
                // method to set the content

                // 👇 Observe LiveData as Compose State
                val uiState =  quoteViewModel.state.observeAsState(
                    initial = QuoteUIState.Loading
                )



                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    when(val state = uiState.value){

                        is QuoteUIState.Loading -> {
                            Text(
                                text = "Loading...",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        is QuoteUIState.Success -> {
                            Column {
                                Spacer(modifier = Modifier.height(32.dp))
                                QuoteCard(
                                    quoteText = state.quote.text,
                                    authorText = state.quote.author
                                )
                            }
                        }

                        is QuoteUIState.Error -> {
                            print("API DATA : ${state.message}")
                            Text(
                                text = state.message,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                    }



                }
            }
        }

//        This method of handling is for the UI created with XML
//        setContentView(R.layout.activity_main)

//        val tvQuote = findViewById<TextView>(R.id.tvQuote)
//        val tvAuthor = findViewById<TextView>(R.id.tvAuthor)

        // we need here observer to watch and load data
//        quoteViewModel.state.observe(this){
//            state ->
//
//            when (state){
//                is QuoteUIState.Loading -> {
//                    // show loading state
//                    tvQuote.text = "Loading..."
//                    tvAuthor.text = ""
//                }
//                is QuoteUIState.Success ->{
//                    tvQuote.text = state.quote.text
//                    tvAuthor.text = "- ${state.quote.author}"
//                }
//                is QuoteUIState.Error -> {
//                    tvQuote.text = state.message
//                    tvAuthor.text = ""
//                }
//            }
//
//        }


        // calling the fetchQuote(id) function to get the quote from the server
        // we can now pass this fetch quote id dynamic to get different data
        quoteViewModel.fetchQuote(1)
    }
}
@Composable
fun QuoteCard(
    quoteText: String,
    authorText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Quote text
            Text(
                text = "“$quoteText”",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Divider
            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
            )

            // Author
            Text(
                text = "- $authorText",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
