# reviewApp

This is a simple demo app to demonstrate the dowload of data and using of pagination with recycler view.

Technologies in use:
- Retrofit
- RxJava
- Robolectric (running integration test)

Offline mode support:

To support offline mode I enabled network caching. Unfortunatly the server doesn't support it so its pretty hacky and dangorous.
Either server should give support, or would need to find other solutions like storing in db. (Room)

Downloading Pagination:

Made my own simple class to perform download of data with retrofit and use pagination. In bigger projects would be nice to consider using Paging Library

Filtering:

Since filtering with Pagination could be confusing to user, (Need to diasble pagination, and wont see al the data) filtering is not implemented.

Posting New Review:

Given that user is authenticated, the body can only contain the provided fields. (Title, comment,travel type)
Response can be simple, a status code review_id should be returned and optional error message.
Would concider adding the number of reviews, to see if new reviews poped up.
Also might be a good idea to return the new review object. In case we would consider using database.

Improvements:

- Better error handling. Adding error handling to send review page
- Adding a retry button in case of failure
- Fix orientation change
- Better use of loading indicator




