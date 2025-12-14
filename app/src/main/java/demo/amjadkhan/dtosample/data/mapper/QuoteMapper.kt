package demo.amjadkhan.dtosample.data.mapper

import demo.amjadkhan.dtosample.data.remote.dto.QuoteDto
import demo.amjadkhan.dtosample.domain.model.Quote


// We will create function to convert QuoteDto to Quote
// this is factory kind of function
fun QuoteDto.toDomain(): Quote {
    return Quote(
      id = id, // passing the id of the quoteDto to the Quote domain model
      text = quote, // quote is the description of the field in the QuoteDto
      author = author
    );
}