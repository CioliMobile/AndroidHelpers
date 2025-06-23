# AndroidHelpers
Android Helpers is my personal library that contains helpful code snippets that I find myself reusing.

## SavedStateNavResultData
Using compose navigation in a CMP project is a bit of a hassle - this class creates a composable
function that can be inserted into any composition that will check for a specified navigation result
using a provided key. This gets around issues that iOS currently experiences with complex navigation
types.
